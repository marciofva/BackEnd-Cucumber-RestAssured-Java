#Author: Marcio Fernando Vieira de Almeida
Feature: Validate CEP from the API - (Correios)

  Background: Endpoint Configuration 
    Given The endpoint is already configured

  @run
  Scenario: Enter a valid CEP
    When I input a valid CEP "13040089"
    Then I should have the status code "200"
    And content type should be in "JSON" format
    And the body response content should be matched
      | key              | value                             |
      | cep              |                          13040089 |
      | tipoDeLogradouro | Rua                               |
      | logradouro       | Manoel Sylvestre de Freitas Filho |
      | bairro           | Jardim Nova Europa                |
      | cidade           | Campinas                          |
      | estado           | SP                                |

  @run
  Scenario: Enter an invalid CEP
    When I input a valid CEP "45683499"
    Then I should have the status code "404"
