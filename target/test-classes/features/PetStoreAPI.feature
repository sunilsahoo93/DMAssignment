@Regression @API @UI
#Author: Sunil Kumar Sahoo
#Creation Date: 29 Oct 2022

Feature: Pet Store API tests

  @Test01
  Scenario Outline: Add a new pet with valid inputs to the store
    Given user is authorized to use Pet Store
    When user adds a new pet with "valid" input to the pet store with payload from file <fileName>
    Then the new pet "is added" with status code <statusCode>
    Examples:
      | fileName    | statusCode |
      | Test01.json | 200        |

  @Test02
  Scenario Outline: Add a new pet with invalid(blank) inputs to the store
    Given user is authorized to use Pet Store
    When user adds a new pet with "invalid" input to the pet store with payload from file <fileName>
    Then the new pet "is not added" with status code <statusCode>
    Examples:
      | fileName    | statusCode |
      | Test02.json | 405        |

  @Test03
  Scenario Outline: Find a pet with valid id
    Given user is authorized to use Pet Store
    When user tries to find a pet with "valid" id <petId>
    Then the expected pet details given in file <fileName> is be returned with status code <statusCode>
    Examples:
      | fileName    | statusCode | petId |
      | Test03.json | 200        | 1     |


  @Test04
  Scenario Outline: Find a pet with invalid id
    Given user is authorized to use Pet Store
    When user tries to find a pet with "invalid" id <petId>
    Then error message given in file <fileName> is returned with status code <statusCode>
    Examples:
      | fileName    | statusCode | petId |
      | Test04.json | 404        | -1    |
