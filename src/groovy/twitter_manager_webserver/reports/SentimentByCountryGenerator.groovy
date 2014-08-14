package twitter_manager_webserver.reports

class SentimentByCountryGenerator {

    def generateGroupedData(tweetsByCampaign) {
        def groupedByCountryAndSentiment = tweetsByCampaign.findAll({it.country != null}).groupBy({ it.country }, {it.sentiment}).sort()

        def countries = groupedByCountryAndSentiment.keySet()
        def positives = groupedByCountryAndSentiment.collect {
            if(it.value.POSITIVE) {
                it.value.POSITIVE.size()
            } else {
                0
            }
        }

        def negatives = groupedByCountryAndSentiment.collect {
            if(it.value.NEGATIVE) {
                it.value.NEGATIVE.size()
            } else {
                0
            }
        }

        def neutrals = groupedByCountryAndSentiment.collect {
            if(it.value.NEUTRAL) {
                it.value.NEUTRAL.size()
            } else {
                0
            }
        }

        return [countries: countries, positives: positives, negatives: negatives, neutrals: neutrals]

    }
}
