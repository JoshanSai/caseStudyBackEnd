package com.example.demo.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Expenses;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Long>{
	
	

	List<Expenses> findByCreatedDate(Date date);
	List<Expenses> findByCommunityId(long id);

	@Query("select e from Expenses e where TO_CHAR(TRANSACTION_DATE,'MM')=:month AND TO_CHAR(TRANSACTION_DATE,'YYYY')=:year")
	List<Expenses> findByMonth(Sort by,@Param("month") String month,@Param("year") String year);
	
	List<Expenses> findByCommunityIdAndTransactionDateBetween(Sort by, long id,Date from,Date to);

	List<Expenses> findById(long id);
}
