Flyway SQL Migrations
=====================

Configure Maven for encrypted server passwords, if you haven't already done so:

    http://maven.apache.org/guides/mini/guide-encryption.html


OS X Username Mapping
=====================

If you are using OS X, update user_mapping.sh to map your funky OS X username
to your database username.  This will make running the Flyway scripts easier.


Flyway Server Entries
=====================

Create at least one <server> entry in your Maven ~/.m2/settings.xml file for
your environment (the encryption guide above shows how to do that).  For
developers, this is something similar to:

    <server>
      <id>cbes-[your username]</id>
      <username>budges2_[your username]</username>
      <password>password goes here</password>
    </server>

Typical server entries (IDs) are:

    cbes-[your username]
    cbes-unittest
    cbes-development
    cbes-staging
    cbes-production


Build Profiles
==============

The POM is set up with build profiles for:

    default     -- developer's schema (can be omitted)
    unittest    -- CBES Unit (Integration) testing database
    development -- CBES development database
    staging     -- CBES staging database
    production  -- CBES production database

Use the -P option with Maven, such as: mvn -Pstaging ...

Prompting for User Name & Password
==================================
You can also have Maven prompt for the user and password for a particular database. This is 
appropriate, for example, when DBAs run Maven to apply database changes via Flyway. 

To do this, you would first simply add a new build profile to the Mavne POM as follows:
(See http://stackoverflow.com/questions/11340894/is-there-a-way-to-capture-user-input-in-maven-and-assign-it-to-a-maven-propertty, esp.
about the bug requiring the ant dependency.)

    <profile>
        <id>promptUser</id>
        <build>
            <plugins>
                <plugin>
                    <artifactId>maven-antrun-plugin</artifactId>
                    <version>1.7</version>
                    <executions>
                        <execution>
                            <id>get-db-creds</id>
                            <goals>
                                <goal>run</goal>
                            </goals>
                            <phase>pre-clean</phase>
                            <configuration>
                                <target>
                                    <input message="Enter USER for DATABASE ${jdbc.url}:"
                                        addproperty="dbUser" />
                                    <input message="Enter PASSWORD for DATABASE ${jdbc.url}:"
                                        addproperty="dbPassword" />
                                </target>
                                <exportAntProperties>true</exportAntProperties>
                            </configuration>
                        </execution>
                    </executions>
                    <dependencies>
                        <dependency>
                            <groupId>org.apache.ant</groupId>
                            <artifactId>ant</artifactId>
                            <version>1.8.4</version>
                        </dependency>
                    </dependencies>
                </plugin>
            </plugins>
        </build>
    </profile>

Then, add the collected user and password properties to the Flyway plugin configuration:

    <plugin>
        <groupId>com.googlecode.flyway</groupId>
        <artifactId>flyway-maven-plugin</artifactId>
        <version>${flyway.version}</version>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.connector.version}</version>
            </dependency>
        </dependencies>
        <configuration>
            <serverId>${settings.key}</serverId>
            <driver>${jdbc.driver}</driver>
            <url>${jdbc.url}</url>
            <user>${dbUser}</user>
            <password>${dbPassword}</password>
        </configuration>
    </plugin>

When you invoke Maven, ensure to include the "promptUser" profile.

Useful Flyway Commands
======================

Check the Flyway schema table for the status of the migrations:

    mvn compile flyway:info


Validate the Flyway migrations before migration (build will fail if migrations
have been tampered with):

    mvn compile flyway:validate

Migrate to latest version:

    mvn compile flyway:migrate -Dflyway.validateOnMigrate=true

Migrate to specific version:

    mvn compile flyway:migrate -Dflyway.validateOnMigrate=true -Dflyway.target=3.8.1

Migrate to latest version allowing out-of-order version numbers (applies skipped migrations):

    mvn compile flyway:migrate -Dflyway.validateOnMigrate=true -Dflyway.outOfOrder=true


Typical Flyway Workflow
=======================

mvn compile flyway:info
    Check the state of the migrations (see if any are pending, for example).

mvn compile flyway:validate
    Validate that no sneaky changes were added to previous migrations.

mvn compile flyway:migrate
    Migrate pending migrations.

Repeat as needed.


Shortcuts
=========

You can use the info.sh, validate.sh, and migrate.sh shortcuts for the longer
Maven commands shown for flyway:info, flyway:validate, and flyway:migrate.

You can also pass parameters (if not using quotes), such as:

    ./info.sh -Pstaging

If using quoted parameters, use the full Maven command.

Note that migrate.sh defaults to -Dflyway.validateOnMigrate=true.  If you need
to override this option, use the full Maven command.  Be sure to append the
flyway.target and/or flyway.outOfOrder options (shown above) if needed.


Failed Flyway Migrations
========================

Occasionally a migration will fail.  When this happens, do:

    mvn compile flyway:info

to see which migration failed (especially important if running multiple
migrations at the same time).  Once you have identified the offending script,
fix it and then potentially fix the database, too.

Even though Flyway runs the migration in a transaction, some databases, such
as MySQL, do not do everything transactionally.  For example, creating a table
will NOT rollback when the transaction fails.  Therefore, you might have to
manually drop the table before re-running the script.

After the offending script has been fixed and any database cleanup performed,
you need to repair the Flyway metadata table with:

    mvn compile flyway:repair

You can then run the migration again.


Initializing an EXISTING Database
=================================

To initialize an existing database (one with data -- such as production, but
no Flyway metadata table):

    mvn compile flyway:init -Dflyway.initVersion=5 -Dflyway.initDescription="Base Version"

This only needs to be done ONCE.


Database Migration Script Tips
==============================

It is suggested you keep DDL/DML migrations separated into one change/file.
This is especially true when working with MySQL which does not support
transactional DDL (create/alter/drop will NOT roll back).  Breaking such
related migrations into separate smaller pieces will make failed migrations
easier to manage.


Managing Out-of-Order
=====================

For lower environments, it is fine to have out-of-order scripts, as long as
you carefully manage the changes and understand the implications.  In the
production environment, however, you want everything to be smooth and orderly.

Occasionally, though, something goes wrong and your production environment
gets to be out-of-order, but you can reset it with a simple SQL command:

    update schema_version set installed_rank = version_rank;

In a normal orderly migration progression, the actual installed rank is always
the same as the calculated version rank.  This update command ensures they are
in sync again.  This doesn't update the timestamp for the actual migration, so
you still have a chronological record of the migration time if needed.
