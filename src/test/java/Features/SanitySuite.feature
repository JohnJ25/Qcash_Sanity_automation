Feature: Qcash Application : Loan Application Flow Sanity Validation
				
@SanityCheck
 Scenario Outline: Validate the Loan application flow for the CU
  
  #Browser launch and url access check
  Given Launch the FI "<sitename>" site "<url>" urls
  Given Enter the valid user credentials "<username>" and "<password>" to access the application
  
  
  #Maintenance Page
  Then Validate the loan application workflow when the Maintenance page is set to Active
  And Validate the loan application workflow when the Maintenance page is set to InActive
    
  #Awareness Page 
  Then Set the Awareness page to Activate and verify the loan application flow
  And Set the Awareness page to InActivate and verify the loan application flow
      
  #FraudControl
  Then Set the Fraud Control to Activate and verify the loan application flow
  And Set the Fraud Control to InActivate and verify the loan application flow
  
  #Mask Initiate Wait Time
  Then Set the Mask Initiate Wait Time to Active and verify the loan application flow
  And Set the Mask Initiate Wait Time to InActive and verify the loan application flow
    
  #AAN
  Then Set the Deny for Max Open Loans Across Products to Active and verify the loan application flow
  And Set the Deny for Max Open Loans Across Products to InActive and verify the loan application flow
  
  
   Examples:
    
	| sitename   						|| url							 			 		                     			|  
	| Vibe Credit Union						   	||  https://testadmin.q-cash.com/vibecu/Authentication/Login   		|
	#| PVFCU 						    	||  https://testadmin.q-cash.com/pvfcu/Authentication/Login			      |




