package twitter_manager_webserver.factories

import twitter_manager_webserver.dto.TweetCampaignDTO
import twitter_manager_webserver.utils.DateParseUtil

class TweetCampaignDTOFactory {


    def createListOfTweetCampaignDTOFromJSONList(listOfTweetsJSON) {
        return listOfTweetsJSON.collect { jsonTweetCampaign ->
            return createTweetCampaignDTOFromJson(jsonTweetCampaign)
        }
    }

    def createTweetCampaignDTOFromJson(tj) {
        TweetCampaignDTO tweetCampaignDTO = new TweetCampaignDTO()
        tweetCampaignDTO.with {
            it.id = tj.id
            it.campaignId = tj.campaign_id
            it.tweetId = tj.tweet_id
            it.text = tj.text
            it.sentiment = tj.sentiment
            it.points = tj.points
            it.retweets = tj.retweets
            it.favorites = tj.favorites
            it.followers = tj.followers
            it.userId = tj.user_id
            it.country = tj.country
            it.countryCode = tj.country_code
            it.tweetDateCreated = DateParseUtil.createDateFrom(tj.tweet_date_created)
        }
        return tweetCampaignDTO
    }
}
