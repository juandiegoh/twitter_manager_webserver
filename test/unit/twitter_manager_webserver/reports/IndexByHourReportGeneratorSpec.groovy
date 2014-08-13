package twitter_manager_webserver.reports

import spock.lang.Specification
import twitter_manager_webserver.dto.TweetCampaignDTO
import twitter_manager_webserver.utils.DateParseUtil

import java.text.SimpleDateFormat

class IndexByHourReportGeneratorSpec extends Specification {

    IndexByHourReportGenerator indexByHourReportGenerator

    void setup() {
        this.indexByHourReportGenerator = new IndexByHourReportGenerator()
    }

    void "given 1 hours must return a IndexByHourReport object with the group by"() {
        given:
        TweetCampaignDTO t1 = createTweetDTO("12-08-2014 16:12:38", "POSITIVE", 20)
        TweetCampaignDTO t2 = createTweetDTO("12-08-2014 16:12:38", "NEGATIVE", -10)
        TweetCampaignDTO t3 = createTweetDTO("12-08-2014 16:12:38", "POSITIVE", 40)
        TweetCampaignDTO t4 = createTweetDTO("12-08-2014 16:12:38", "NEGATIVE", -20)
        TweetCampaignDTO t5 = createTweetDTO("12-08-2014 16:12:38", "NEUTRAL", 0)

        when:
        def result = indexByHourReportGenerator.groupByTweets([t1, t2, t3, t4, t5])

        then:
        result.hours == ["12 16"]
        result.positives == [30]
        result.negatives == [-15]
    }

    void "given 2 hours must return a IndexByHourReport object with the group by"() {
        given:
        TweetCampaignDTO t1 = createTweetDTO("12-08-2014 16:12:38", "POSITIVE", 20)
        TweetCampaignDTO t2 = createTweetDTO("12-08-2014 16:12:38", "NEGATIVE", -10)
        TweetCampaignDTO t3 = createTweetDTO("12-08-2014 16:12:38", "POSITIVE", 40)
        TweetCampaignDTO t4 = createTweetDTO("12-08-2014 16:12:38", "NEGATIVE", -20)
        TweetCampaignDTO t5 = createTweetDTO("12-08-2014 16:12:38", "NEUTRAL", 0)
        TweetCampaignDTO t6 = createTweetDTO("12-08-2014 17:12:38", "POSITIVE", 20)

        when:
        def result = indexByHourReportGenerator.groupByTweets([t1, t2, t3, t4, t5, t6])

        then:
        result.hours == ["12 16", "12 17"]
        result.positives == [30, 20]
        result.negatives == [-15, 0]
    }

    void "given multiple hours must return a IndexByHourReport object with the group by"() {
        given:
        TweetCampaignDTO t1 = createTweetDTO("12-08-2014 16:12:38", "POSITIVE", 20)
        TweetCampaignDTO t2 = createTweetDTO("12-08-2014 16:12:38", "NEGATIVE", -10)
        TweetCampaignDTO t3 = createTweetDTO("12-08-2014 16:12:38", "POSITIVE", 40)
        TweetCampaignDTO t4 = createTweetDTO("12-08-2014 16:12:38", "NEGATIVE", -20)
        TweetCampaignDTO t5 = createTweetDTO("12-08-2014 16:12:38", "NEUTRAL", 0)
        TweetCampaignDTO t6 = createTweetDTO("12-08-2014 17:12:38", "POSITIVE", 20)
        TweetCampaignDTO t7 = createTweetDTO("12-08-2014 18:12:38", "NEGATIVE", -10)

        TweetCampaignDTO t8 = createTweetDTO("12-08-2014 19:12:38", "NEGATIVE", -10)
        TweetCampaignDTO t9 = createTweetDTO("12-08-2014 19:12:38", "NEGATIVE", -20)
        TweetCampaignDTO t10 = createTweetDTO("12-08-2014 19:12:38", "NEGATIVE", -30)

        TweetCampaignDTO t11 = createTweetDTO("12-08-2014 19:12:38", "POSITIVE", 60)

        when:
        def result = indexByHourReportGenerator.groupByTweets([t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11])

        then:
        result.hours == ["12 16", "12 17", "12 18", "12 19"]
        result.positives == [30, 20, 0, 60]
        result.negatives == [15, 0, 10, 20]
    }

    def createTweetDTO(dateCreated, sentiment, points) {
        TweetCampaignDTO tweet = new TweetCampaignDTO()
        tweet.with {
            it.tweetDateCreated = DateParseUtil.createDateFrom(dateCreated)
            it.sentiment = sentiment
            it.points = points
        }
        return tweet
    }
}

