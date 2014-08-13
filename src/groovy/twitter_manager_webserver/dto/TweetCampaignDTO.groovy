package twitter_manager_webserver.dto

class TweetCampaignDTO {

    def id
    Long campaignId
    String tweetId
    String text
    String sentiment
    Long points
    Long retweets
    Long favorites
    Long followers
    String userId
    String country
    String countryCode
    Date tweetDateCreated
}
