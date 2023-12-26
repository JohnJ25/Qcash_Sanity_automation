Feature: Qcash Application : Loan Application Flow Sanity Validation
				
@SanityCheck
 Scenario Outline: Validate the Loan application flow for the CU
  
  #Browser launch and url access check
  Given Launch the FI "<sitename>" site "<url>" urls
  Given Enter the valid user credentials "<username>" and "<password>" to access the application
  
  
  #Maintenance Page
 # Then Select the Maintenance Active and complete the loan application flow
 # And Select the Maintenance InActive and complete the loan application flow
    
  #Awareness Page 
  Then Select the Awareness page Active and complete the loan application flow
  And Select the Awareness page InActive and complete the loan application flow
    
    
  #FraudControl
  Then Select the Fraud Control page and Activate the FraudControl setting and verify the loan application flow
  And Select the Fraud Control page and InActivate the FraudControl setting and verify the loan application flow
  
  #MaskWaitTime
  
    
   Examples:
    
	| sitename   						|| url							 			 		                     			|  
	| 4Fornt						   	||  https://testadmin.q-cash.com/4frontcu/login   		|
	#| PVFCU 						    	||  https://testadmin.q-cash.com/pvfcu/Authentication/Login			      |




