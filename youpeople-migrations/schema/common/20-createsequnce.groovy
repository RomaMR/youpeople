databaseChangeLog {
    changeSet(id: 'hibernate_sequence', author: 'mudryi_roman') {
        comment('Added sequence generator.')
        createSequence(sequenceName: 'hibernate_sequence', startValue: '300', incrementBy: '1')
    }
}