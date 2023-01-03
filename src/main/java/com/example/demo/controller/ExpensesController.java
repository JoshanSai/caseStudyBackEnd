package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Expenses;
import com.example.demo.repository.ExpensesRepo;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ExpensesController {

	@Autowired
	ExpensesRepo expensesRepo;

	   @GetMapping("/expenses")
	    public List < Expenses > getAllExpenses() {
		   System.out.println("inside get request");
	        return expensesRepo.findAll();
	    }
	   
	   @PostMapping("/putExpenses")
	    public Expenses putExpenses( @RequestBody Expenses expenses) {
	              expensesRepo.save(expenses);
	              return expenses;
	   };
	   @GetMapping("/expenses/{id}")
	    public List < Expenses > getAllExpensesByCommunityId(@PathVariable(value = "id") long id) {
		   System.out.println("inside get request");
	        return expensesRepo.findByCommunityId(id);
	    }
	   @GetMapping("/expenses/byMonthAndComId/{month}/{year}/{comId}")
	    public List < Expenses > getAllExpensesByMonth(@PathVariable(value = "month") String month,@PathVariable(value = "year") String year,@PathVariable(value = "comId") Long comId) {
		   System.out.println("inside get request");
		   List<Expenses> expenses = new ArrayList<>();
		   List<Expenses> returningExpenses = new ArrayList<>();
		   expenses = expensesRepo.findByMonth(month,year);
		   for (int i = 0; i<expenses.size(); i++) {
			   if (expenses.get(i).communityId == comId) {
				   returningExpenses.add(expenses.get(i));
			   }
		   }
	        return returningExpenses;
	        
	    }		
	   @PutMapping("/updateExpenses/{id}/{flatId}")
		public ResponseEntity<Expenses> updateTutorial(@PathVariable("id") long id,@PathVariable("flatId") long flatId, @RequestBody Expenses expenses) {
			List<Expenses> tutorialData = expensesRepo.findById(id);

			if (!tutorialData.isEmpty()) {
				Expenses _tutorial = tutorialData.get(0);
				_tutorial.setAmount(expenses.amount);
				_tutorial.setTransactionDate(expenses.transactionDate);
				_tutorial.setFlatId(flatId);
				_tutorial.setType(expenses.type);
				return new ResponseEntity<>(expensesRepo.save(_tutorial), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	   @GetMapping("/expensesRange/{from}/{to}/{comId}")
	    public List < Expenses > getAllExpensesByDateRange(@PathVariable(value = "from") Date from,
	    		@PathVariable(value = "to") Date to,@PathVariable(value="comId") long comId) {
		   List<Expenses> expenses = new ArrayList<>();
	        List<Expenses>str=expensesRepo.findByTransactionDateBetween(from, to);
	        for(int i=0;i<str.size();i++) {
	        	if(str.get(i).communityId==comId) {
	        		expenses.add(str.get(i));
	        	}
	        }
	        return expenses;
	    }
	   
}
	