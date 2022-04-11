# poll_app_spring_mvc
Implemented for the polling system:

The poll includes several questions. Each question can have multiple answers. Each answer option has a weight. There can be several results for each poll, depending on the number of points scored by the user during the survey.
The result of the poll includes the corresponding range of scores and decoding. To determine the result, the system must sum the weights for each user response.Implemented functionality allowing the user to take one survey no more than once a week. 
If less than a week has passed since the last pass, the result of the last pass is shown to the user.

In application ther are` 
I18n,
bean validation,
exception handing,
loggers,
thymeleaf/html,
there are pages design with css,
created JDBC connection.

The application has 7 pages:

Login page 
![image](https://user-images.githubusercontent.com/93138699/162825254-dc105796-36e0-4657-bf0d-1b6ce293a104.png)
Registration page
![image](https://user-images.githubusercontent.com/93138699/162825367-7b7fcbbd-0309-435b-b8bf-651552187d26.png)
Home page where information about user and poll button

List of polls (tests)
![image](https://user-images.githubusercontent.com/93138699/162825659-6b8285c2-8c82-424c-8d14-21eadf3d6247.png)

Page taking a poll
Results
![image](https://user-images.githubusercontent.com/93138699/162825821-a6815c1e-dcab-4d54-9c87-c1517fba4722.png)
Error page
![image](https://user-images.githubusercontent.com/93138699/162828444-e54e5588-f97d-476a-80b8-809bf3627e94.png)

