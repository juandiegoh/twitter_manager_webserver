package twitter_manager_webserver.repositories

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import twitter_manager_webserver.factories.TweetCampaignDTOFactory
import twitter_manager_webserver.utils.DateParseUtil
import twitter_manager_webserver.utils.RetryUtil

class RestTweetCampaignRepository implements TweetCampaignRepository {

    private static final String API_URL = "http://localhost:8080/twitter_manager_consumer/"

    TweetCampaignDTOFactory tweetCampaignDTOFactory

    @Override
    def findAllByCampaignIdBetween(campaignId, dateFrom, dateTo) {
        String dateFromString = DateParseUtil.getStringFromDate(dateFrom)
        String dateToString = DateParseUtil.getStringFromDate(dateTo)

        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder(API_URL)
            response = RetryUtil.retry(3, 100) {
                return httpBuilder.post(
                        path: 'tweets',
                        requestContentType: ContentType.JSON,
                        body: [campaign_id: campaignId,
                                date_from: dateFromString,
                                date_to: dateToString]
                )
            }
        } catch (e) {
            log.error "Hubo un error al leer de la api de tweets", ${e}
        }
        return tweetCampaignDTOFactory.createListOfTweetCampaignDTOFromJSONList(response)
    }
}
