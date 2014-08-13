package twitter_manager_webserver

import twitter_manager_webserver.reports.IndexByHourReportGenerator
import twitter_manager_webserver.repositories.CampaignRepository
import twitter_manager_webserver.repositories.TweetCampaignRepository

class CampaignService {

    CampaignRepository campaignRepository
    TweetCampaignRepository tweetCampaignRepository
    IndexByHourReportGenerator indexByHourReportGenerator

    def findAll() {
        return this.campaignRepository.findAll()
    }

    def getReportsDataFromTweetsOfCampaign(campaignId, dateFrom, dateTo) {
        def tweetsByCampaign = this.tweetCampaignRepository.findAllByCampaignIdBetween(campaignId, dateFrom, dateTo)
        def indexByHourData = indexByHourReportGenerator.groupByTweets(tweetsByCampaign)
        return indexByHourData
    }
}
