Feature: Logging into site
  Logging with valid login and password. Adding some post and checking filtering posts.

  # авторизация на сайте
  Example: Logging in to site
  Opening main page (you should be logged in for doing that? That's why login page would be opened).
    When open "mainPage" page
    And "logInPage" page opened
    And type to input with name "username" text: "u"
    And type to input with name "password" text: "p"
    And press button with value "Войти"
    Then "mainPage" page should be opened

  # добавление записи в БД
  Scenario Outline: Adding some posts
  Typing some posts in site.
    Given opened "mainPage" page
    When type to input with name "text" text: "<text>"
    And type to input with name "tag" text: "<tag>"
    And press button with value "Send"
    Then message with tag: "<tag>" should appear
    Examples:
      | text                                                                           | tag      |
      | Ut enim ad minim veniam, quis nostrud exercitation ullamco                     | keyboard |
      | Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor | Lorem    |
      | Excepteur sint occaecat cupidatat non proident, sunt in culpa qui official     | Ex       |

  # поиск записи на сайте
  Scenario: Searching posts
  Checking whether searching work or not.
    Given opened "mainPage" page
    When type to input with name "filter" text: "Lorem"
    And press button with value "Search"
    Then message with tag: "Lorem" should appear


  # выход с учетной записи
  Scenario: Logout
  Logout from your account. You should be redirected to login page.
    When pressed button with value "Sign Out"
    Then "logOutPage" page should be opened