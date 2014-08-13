package twitter_manager_webserver.repositories

import twitter_manager_webserver.dto.CampaignDTO

public interface CampaignRepository {

    def findAll()
    def save(CampaignDTO campaignDTO)
    def turnOn(campaignId)
    def turnOff(campaignId)
}