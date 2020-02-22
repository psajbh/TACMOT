#! /bin/bash

# Attempts to map funky OS X usernames to something sensible.

# Guillermo:  1285198883@mil => gsuchicital
# Jennifer: 1514576994@mil => jtrow
# Chris:    1385369596@mil => cmiller
# Michael:  1300258650@mil => mgentry

DATABASE_USERNAME=""

case "`whoami`" in
    "1285198883@mil") DATABASE_USERNAME="gsuchicital" ;;
    "1514576994@mil") DATABASE_USERNAME="jtrow"     ;;
    "1385369596@mil") DATABASE_USERNAME="cmiller"  ;;
    "1300258650@mil") DATABASE_USERNAME="mgentry"   ;;
    "1540332807") DATABASE_USERNAME="ewood"   ;;
	"JHart") DATABASE_USERNAME="jhart"  ;;
	"John") DATABASE_USERNAME="jhart"  ;;
esac

if [ -z "$DATABASE_USERNAME" ]; then
    echo "Unknown database username..."
else
    echo "Using database username: $DATABASE_USERNAME"
    
    FLYWAY_USERNAME="-Duser.name=$DATABASE_USERNAME"
fi
