Play project with mapstruct
=================================
Play Framework Project with MapStruct Example


# Getting Started
To run sbt project, you need to provide proper environment variables with the command, it will
not work without the correct DB configuration:

````
sbt -Xms512M -Xmx1024M -Xss1M -XX:+CMSClassUnloadingEnabled -Dconfig.file=conf/development.conf
````

# Initial data
You can see `initialAccount` part inside `application.conf` file for the master email/password

# Initial DB
If you load `development.conf` then you can see the db info you need to create there, otherwise, it is on `application.conf`
