# login_verification

This is AspectJ project.
The project simulates the process of login to website. 
If a user tries to login for the first time, he will be asked
to pass the registration process.
After that the system will check the location of a user. 
If a registered user tries to connect, he will have to pass 
the identification process. 
After successful identification a file that simulates 
cookie will be created.
If a user's location is valid and a user is not in the site's
blacklist  - he will be connected.
Disconnection happens either proactively or forcibly. 
Before it happens session id'll be written into a 
cookie document that was created before.
● The entire process is performed while documenting actions
 in file and encrypting user inputs
 
 Aspects description:
 1. Documentation aspect - documents all actions during the process
  of loggin
 
 2. Encryption aspect - performs encryption of user inputs
    Description aspect - performs decryption
    * Encryption is performed using an algorithm that exchanges 
      each letter for the next letter in alphabetical order,
      and each digit is switched to the next digit in ascending
      order.
      
 3. CookieCreation aspect - creates a file that simulates a
    cookie. The aspect takes an ip address of a computer 
    from which user wants to connect, and creates a file 
    (file name is an ip address)
    
 4. Location aspect - checks the location of a computer from
    which user is trying to connect (the check is done using
    the website https://geo.ipify.org/)
   
 5. BlackList aspect - checks the validity of time that passed
    between user's logins.
    The aspect checks the difference between the time of 
    current login and the time of last login of the same user.
    If difference is smaller than the time specified in the 
    aspect - it'll be treated as an illegal connection.
    
  6. Forced disconnection aspect - if a user is logged in for 
     too long (the maximum time is defined in the aspect - 
     he will be forcibly disconnected.
     
 7. Update cookie aspect - before logging out the aspect updates
    the file that simulates a cookie 
