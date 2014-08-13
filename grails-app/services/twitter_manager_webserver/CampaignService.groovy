package twitter_manager_webserver

import twitter_manager_webserver.dto.CampaignDTO
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

    def save(CampaignDTO campaignDTO) {
        def id = this.campaignRepository.save(campaignDTO)
        campaignDTO.id = id
        return campaignDTO
    }

    def turnOn(campaignId) {
        return this.campaignRepository.turnOn(campaignId)
    }

    def turnOff(campaignId) {
        return this.campaignRepository.turnOff(campaignId)
    }
}
