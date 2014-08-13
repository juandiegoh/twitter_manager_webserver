package twitter_manager_webserver.reports

class IndexByHourReportGenerator {

    def groupByTweets(tweetCampaignDTOs) {
        def grouped = tweetCampaignDTOs.findAll { it.sentiment != 'NEUTRAL' }.groupBy({ it.tweetDateCreated.format('dd HH')}, { it.sentiment })
        def hours = grouped.keySet().sort()
        def positives = grouped.sort().collect {
            if(it.value.POSITIVE) {
                it.value.POSITIVE.sum { it.points }/it.value.POSITIVE.size()
            } else {
                0
            }
        }
        def negatives = grouped.sort().collect {
            if(it.value.NEGATIVE) {
                Math.abs(it.value.NEGATIVE.sum { it.points }/it.value.NEGATIVE.size())
            } else {
                0
            }
        }
        return [hours: hours, positives: positives, negatives: negatives]
    }
}
