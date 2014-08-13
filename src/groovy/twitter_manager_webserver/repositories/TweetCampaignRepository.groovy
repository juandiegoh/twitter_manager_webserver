package twitter_manager_webserver.repositories

interface TweetCampaignRepository {
    def findAllByCampaignIdBetween(campaignId, dateFrom, dateTo)
}
