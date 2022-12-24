// the triggers directive allows us to specify how we want the pipeline to be triggered. 
// - Ref: https://www.jenkins.io/doc/book/pipeline/syntax/#cron-syntax cron helps to create a schedule to run the pipeline using the current string format, starting with the minute, hour, day of the month, month and day of the week
// enkins cron syntax
// The Jenkins cron syntax follows the syntax of the cron utility (with minor differences). Specifically, each line consists of 5 fields separated by TAB or whitespace:

// MINUTE: Minutes within the hour (0–59). can also be written as H so that Jenkins can distribute that load when traffic is not heavy, so is it distributed within the 0-59 mins

// HOUR: The hour of the day (0–23). When you specify a slash and a number eg. */2 it means that we are executing every interval of that number within a range. in this case it would be every 2 hours

// DOM: The day of the month (1–31). if it has a dash between the numbers, eg. 1-3 it means, we are specifying 1st to 3rd day of the month.

// MONTH: The month (1–12). if it has a comma eg 1,3 it means the job need to run only in January and March

// DOW: The day of the week (0–7) where 0 and 7 are Sunday. if it has a dash, eg. 2-4 it means we are specifying Tuesday to Thursday

pipeline {
    agent any
    triggers {
        // minute, hour, day of month, month, day of week (0 and 7 are sundays)
        cron('0 1 2 3 1-7') // --> 0 minute, at 1am, on the second day in the month of march, on any day of the week
        
        // cron('H */2 1-3 1,3 2-4') --> The last time it would have run would be on Thursday, March 3, 2022 at 10:51:14 PM Coordinated Universal Time; would next run at Tuesday, January 3, 2023 at 12:51:14 AM Coordinated Universal Time.
        
        //@yearly, @annualy, @monthly, @weekly, @daily, @hourly
        // cron('@daily')
        
        // cron('TZ=Europe/London\n0 1 * * 1-7') --> please note the line breake character becase the time zone needs to be a separate time from the rest of the cron details
        // Would last have run at Saturday, December 24, 2022 at 1:00:27 AM Coordinated Universal Time; would next run at Sunday, December 25, 2022 at 1:00:27 AM Coordinated Universal Time.
    }
    stages{
        stage('Build'){
            steps {
                echo "Hello World"
            }
        }
    }
}