package twitter_manager_webserver.reports

class FollowersByCategoryGenerator {

    def generateGroupedData(tweetsByCampaign) {
        def grouped = tweetsByCampaign.findAll { it.sentiment != 'NEUTRAL' }.groupBy({getCategory(it.followers) }, { it.sentiment }).sort()
        def categories = grouped.keySet()
        def positives = grouped.collect {
            if(it.value.POSITIVE) {
                it.value.POSITIVE.sum { it.points }/it.value.POSITIVE.size()
            } else {
                0
            }
        }
        def negatives = grouped.collect {
            if(it.value.NEGATIVE) {
                Math.abs(it.value.NEGATIVE.sum { it.points }/it.value.NEGATIVE.size())
            } else {
                0
            }
        }

        [categories: categories, positives: positives, negatives: negatives]
    }

    def getCategory(followers) {
            switch (followers) {
                case 0: "Ninguno"
                    break
                case { it >= 1 && it <= 50 }: "Solo amigos"
                    break
                case { it >= 51 && it <= 100 }: "Notorio"
                    break
                case { it >= 101 && it <= 500 }: "Sociable"
                    break
                case { it >= 501 && it <= 1000 }: "Vale leerlo"
                    break
                case { it >= 1001 && it <= 2000 }: "Amistoso"
                    break
                case { it >= 2001 && it <= 5000 }: "Persona de interés"
                    break
                case { it >= 5001 && it <= 10000 }: "Muy popular"
                    break
                case { it >= 10001 && it <= 50000 }: "Estrella ascendente"
                    break
                case { it >= 50001 && it <= 100000 }: "Casi famoso"
                    break
                case { it >= 100001 && it <= 500000 }: "Famoso"
                    break
                case { it >= 500001 && it <= 5000000 }: "Estrella"
                    break
                default: "Pop star"
                    break
            }
    }
}
