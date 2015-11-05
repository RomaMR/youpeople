databaseChangeLog {
    include(file: 'youpeople-migrations/schema/job/job-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/job/10-addprimarykey-job.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/job/20-modifydatatipe-startdate-enddate-job.groovy', relativeToChangelogFile: false)
}