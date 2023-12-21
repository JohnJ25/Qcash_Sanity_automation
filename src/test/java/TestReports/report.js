$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("D:/Projects/CucumberwithSelenium/InteQ-Web-Automation/src/test/java/Features/CucumberHooks.feature");
formatter.feature({
  "line": 2,
  "name": "Validating Cucumber Hooks Feature",
  "description": "",
  "id": "validating-cucumber-hooks-feature",
  "keyword": "Feature"
});
formatter.before({
  "duration": 14570657862,
  "status": "passed"
});
formatter.scenario({
  "line": 28,
  "name": "To verify the CustomerIQ Collaboration Module",
  "description": "",
  "id": "validating-cucumber-hooks-feature;to-verify-the-customeriq-collaboration-module",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 27,
      "name": "@SmokeTest"
    }
  ]
});
formatter.step({
  "line": 30,
  "name": "Hooks Launch ApplicationURL",
  "keyword": "Given "
});
formatter.step({
  "line": 31,
  "name": "Login to the Application",
  "keyword": "When "
});
formatter.step({
  "line": 32,
  "name": "Landed to CustomerIQ Page",
  "keyword": "And "
});
formatter.step({
  "line": 33,
  "name": "Clicking upon Collaboration tab",
  "keyword": "When "
});
formatter.step({
  "line": 34,
  "name": "Verify Collaboration page",
  "keyword": "Then "
});
formatter.match({
  "location": "Hooks.Hooks_Launch_ApplicationURL()"
});
formatter.result({
  "duration": 6222465182,
  "status": "passed"
});
formatter.match({
  "location": "Hooks.Login_to_the_Application()"
});
formatter.result({
  "duration": 5122522892,
  "status": "passed"
});
formatter.match({
  "location": "Hooks.landed_to_CustomerIQ_Page()"
});
formatter.result({
  "duration": 1481612819,
  "status": "passed"
});
formatter.match({
  "location": "Hooks.clicking_upon_Collaboration_tab()"
});
formatter.result({
  "duration": 6294886766,
  "status": "passed"
});
formatter.match({
  "location": "Hooks.verify_Collaboration_page()"
});
formatter.result({
  "duration": 611120157,
  "status": "passed"
});
formatter.after({
  "duration": 12309208356,
  "status": "passed"
});
});