package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Flats;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.CommunitiesRepo;
import com.example.demo.repository.FlatsRepo;

@RestController
@CrossOrigin("http://localhost:4200/")
public class BookingController {

	@Autowired
	BookingRepo bookingRepo;
	@Autowired
	CommunitiesRepo communityRepo;
	@Autowired
	FlatsRepo flatsRepo;
	
	@PostMapping("/putBooking")
    public Booking putBooking( @RequestBody Booking booking) {
//		Booking submit = new Booking();
//		submit.setFromDate()
		if(booking.approvedByManager=="") {
			booking.setApprovedByManager("pending");
		}
             return bookingRepo.save(booking);
   }
	 @GetMapping("/getBookings/{flat-id}/{type}/{name}")
	    public List<Booking> getCommunityById(@PathVariable(value = "flat-id") long id,@PathVariable(value = "type") String type
	    		,@PathVariable(value = "name") String name) {
	        List<Booking> coms = bookingRepo.findByFlatIdAndTypeAndCreatedBy(id,type,name);
	        if(coms.isEmpty()) {
	        	return null;
	        }
	        else {
	        	return coms;
	        } 
	    }
	 @GetMapping("/getBookingsByName/{name}/{status}")
	    public List<Booking> getBookingDetailsByRoomName(@PathVariable(value = "name") String name,@PathVariable(value = "status") String status) {
		 System.out.print(status);
		 if(status.equalsIgnoreCase("Approved")) {   
			 System.out.print("yeahhh");
		 List<Booking> coms = bookingRepo.findByRoomNameAndApprovedByManager(name,status);
	        	return coms;
	        }
	     else {
	    	 return null;
	     }
	 }
	 @GetMapping("/allBookings/{type}")
	    public List < Booking > getAllBookings(@PathVariable(value = "type") String type) {
		   System.out.println("inside get request");
	        return bookingRepo.findByType(Sort.by(Sort.Direction.DESC,"id"),type);
	    }
	 @GetMapping("/status/{id}/{status}")
	    public Booking  updateEmployee(@PathVariable(value = "id") long id,@PathVariable(value = "status") String status){ 
	        Booking book = bookingRepo.findById(id).get(0);
	        book.setApprovedByManager(status);
	       return bookingRepo.save(book);
	    }
	 @PostMapping("/putBookingF")
	    public Booking putBookingF( @RequestBody Booking booking) {
			if(booking.approvedByManager=="") {
				booking.setApprovedByManager("pending");
			}
	             return bookingRepo.save(booking);
	   }
		 @GetMapping("/getBookingsF/{flat-id}/{type}/{name}")
		    public List<Booking> getByFlatIdAndType(@PathVariable(value = "flat-id") long id,@PathVariable(value = "type") String type
		    		,@PathVariable(value = "name") String name) {
		        List<Booking> coms = bookingRepo.findByFlatIdAndTypeAndCreatedBy(id,type,name);
		        if(coms.isEmpty()) {
		        	return null;
		        }
		        else {
		        	return coms;
		        } 
		    }
		 @GetMapping("/allBookingsF/{type}")
		    public List < Booking > getAllBookingsF(@PathVariable(value = "type") String type) {
			   System.out.println("inside get request");
		        return bookingRepo.findByType(Sort.by(Sort.Direction.DESC,"id"),type);
		    }
		 @GetMapping("/statusF/{id}/{status}")
		    public Booking  updateEmployeeF(@PathVariable(value = "id") long id,@PathVariable(value = "status") String status){ 
		        Booking book = bookingRepo.findById(id).get(0);
		        book.setApprovedByManager(status);
		       return bookingRepo.save(book);
		    }
		
		 //========================================================
		 @PostMapping("/putBooking1/{name}/{status}")
		    public List<Date> putBooking1( @RequestBody Booking booking,@PathVariable(value = "name") String name,
		    		@PathVariable(value = "status") String status) {
			int c=0;	
			 if(booking.approvedByManager=="") {
					booking.setApprovedByManager("pending");
				}
				List<Booking> str = new ArrayList<Booking>();
				List<Date> str2 = new ArrayList<Date>();
				str=bookingRepo.findByRoomNameAndApprovedByManager(name,status);
				for(int i=0;i<str.size();i++) {
					if( booking.fromDate.compareTo(str.get(i).fromDate)>=0 && 
					    booking.fromDate.compareTo(str.get(i).toDate)<=0 || 
                        booking.fromDate.compareTo(str.get(i).fromDate)<=0 && 
                        booking.toDate.compareTo(str.get(i).toDate)>=0) {
						c=c+1;
						System.out.print("helooo"+c);
						str2.add(str.get(i).fromDate);
						str2.add(str.get(i).toDate);
					}
				}
				if(c!=0) {
					return str2;
				}
				else {
					 bookingRepo.save(booking);
					 return null;
				}
		   }
		 @GetMapping("/allBookings/{type}/{comId}")
		    public List < Booking > getAllBookingsByTypeAndCommunity(@PathVariable(value = "type") String type,
		    		@PathVariable(value = "comId") long comId) {
			   List<Booking> str=bookingRepo.findByType(Sort.by(Sort.Direction.DESC,"id"),type);
			   List<Booking> str1 = new ArrayList<Booking>();
			   for(int i=0;i<str.size();i++) {
				   List<Flats> f = flatsRepo.findById(str.get(i).flatId);
				   if(f.get(0).communityId==comId) {
					   str1.add(str.get(i));
				   }
			   }
		        return str1;
		    }

		 }
