Feature: Qcash Application : Loan Application Flow Sanity Validation
				
@SanityCheck
 Scenario Outline: Validate the Loan application flow for the CU
  
  #Browser launch and url access check
  Given Launch the FI "<sitename>" site "<url>" urls
  Given Enter the valid user credentials "<username>" and "<password>" to access the application
  
  #Maintenance Page
  Then From the application dashboard page select the manage fi configuration menu link
  When Click on the loan application menu link
  And Set the maintenance to active or inactive and save the page
  Then Nagivate back to the application dashboard page and select the QA Test suite page
  
  #Loan application launch
  Then Click on the web test primary and secondary and verify the manintenance page 
    
   Examples:
    
	| sitename   						|| url							 			 		                     			|  
	| 4Fornt						   	||  https://testadmin.q-cash.com/4frontcu/login   		|
	#| PVFCU 						    	||  https://testadmin.q-cash.com/pvfcu/Authentication/Login			      |




