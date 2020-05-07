# E-CommerceAppForAndroid
Connect your app to firebase app to the following

Create Admins in your realtime db and add
the following 
create child +25407******* --this should be your phone number under the Admins
create the following children under the child of your phone number
   
    name:**then wirite your name as value" 
    password:**give your password value*
    phone: **give your phone number value**



##DEFINE YOUR REALTIMEDATABASE RULES AS FOLLOWS###
{
  "rules": {
    ".read": true,
    ".write": true
  }
}


##DEFINE YOUR STORAE RULES AS FOLLOWS###
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if request.auth == null;
    }
  }
}
