import twitter_manager_webserver.CampaignService
import twitter_manager_webserver.factories.CampaignDTOFactory
import twitter_manager_webserver.factories.TweetCampaignDTOFactory
import twitter_manager_webserver.reports.IndexByHourReportGenerator
import twitter_manager_webserver.repositories.RestCampaignRepository
import twitter_manager_webserver.repositories.RestTweetCampaignRepository
import twitter_manager_webserver.repositories.TweetCampaignRepository

// Place your Spring DSL code here
beans = {

    campaignService(CampaignService) {
        campaignRepository = ref('restCampaignRepository')
        tweetCampaignRepository = ref('restTweetCampaignRepository')
        indexByHourReportGenerator = ref('indexByHourReportGenerator')
    }

    restCampaignRepository(RestCampaignRepository) {
        campaignDTOFactory = ref('campaignDTOFactory')
    }

    restTweetCampaignRepository(RestTweetCampaignRepository) {
        tweetCampaignDTOFactory = ref('tweetCampaignDTOFactory')
    }

    campaignDTOFactory(CampaignDTOFactory)
    tweetCampaignDTOFactory(TweetCampaignDTOFactory)

    indexByHourReportGenerator(IndexByHourReportGenerator)
}
