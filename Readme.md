# Lab 2: Integration Phase 

## Features & Use Cases
### Before Business Travel 
* Employee Authentication [ Optional ]

* Manager Authentication [ Optional ]

* Employee can choose the cheapest flight, hotels and car rents [ using other groups services ]

* Employee can submit a business travel with the given choices.

* Manager approve the business travel plan or decline it

* If approved, the employee will receive an email from the manager which contain a summary of the business travel.
### During Business Travel
* Employee save collected evidences in the system ( taxi receipt, restaurant bills ,....)

### After Business Travel
* Employee will send his travel expenses by email.

* The travel expenses are automatically analyzed to perform the report of the business travel via cron-jobs

* The manager can see full report

* System verification: 
   - If the total amount is less than the threshold then : system automatically refund the employee 
   - If the total amount is greater than the threshold then : the employee should send justifications ( in this case the manager
   have to accept or refuse the extras fees)
   - When the system trigger a refund for an employee, all the pieces are archived on an FTP.
   

  
## Planning

* Week 1: Familiarization with Camel using some examples, Architecture and Flow

* Week 2: Define a simple skelton as well as implementing different needed connectors and integration of other groups services + Report

* Week 3: Implementation of new services defined in the architecture done in Week 1, and start integrations ( tests ) + Report

* Week 4: Continuation of the integration + Monitoring + Tests + Report

* Week 5: Tests, fixing bugs + finalization of the report







