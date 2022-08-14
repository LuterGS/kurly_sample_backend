package kurly.master.kurly_sample_backend

import kurly.master.kurly_sample_backend.repository.*
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import javax.annotation.PostConstruct

@Configuration
class PostConstructConfiguration(
    private val productJpaRepository: ProductJpaRepository,
    private val metricJpaRepository: MetricJpaRepository,
    private val productMetricJpaRepository: ProductMetricJpaRepository,
    private val productPriceHistoryJpaRepository: ProductPriceHistoryJpaRepository,
    private val metricHistoryJpaRepository: MetricHistoryJpaRepository
) {

    @PostConstruct
    fun insertData(){
        val p1 = Product(null, "배 450g", 4500, "많은 양이 필요하지 않을 때 실속 1입", "https://www.kurly.com/goods/5072705", "https://img-cf.kurly.com/shop/data/goodsview/20210507/gv30000180785_1.jpg")
        val p2 = Product(null, "[fruit salon] 사과 300g", 2990, "간편하게 즐기는 조각 사과", "https://www.kurly.com/goods/5063862", "https://img-cf.kurly.com/shop/data/goodsview/20210126/gv40000154376_1.jpg");
        this.productJpaRepository.saveAll(listOf(p1, p2))

        val pph11 = ProductPriceHistory(null, p1, LocalDateTime.of(2022, 8, 7, 14, 0, 2), 4450)
        val pph12 = ProductPriceHistory(null, p1, LocalDateTime.of(2022, 8, 8, 14, 0, 2), 4550)
        val pph13 = ProductPriceHistory(null, p1, LocalDateTime.of(2022, 8, 9, 14, 0, 2), 4500)
        val pph14 = ProductPriceHistory(null, p1, LocalDateTime.of(2022, 8, 10, 14, 0, 2), 4480)
        val pph15 = ProductPriceHistory(null, p1, LocalDateTime.of(2022, 8, 11, 14, 0, 2), 4320)
        val pph16 = ProductPriceHistory(null, p1, LocalDateTime.of(2022, 8, 12, 14, 0, 2), 4500)
        this.productPriceHistoryJpaRepository.saveAll(listOf(pph11, pph12, pph13, pph14, pph15, pph16))

        val pph21 = ProductPriceHistory(null, p2, LocalDateTime.of(2022, 8, 7, 11, 0, 2), 2990)
        val pph22 = ProductPriceHistory(null, p2, LocalDateTime.of(2022, 8, 8, 11, 0, 2), 2880)
        val pph23 = ProductPriceHistory(null, p2, LocalDateTime.of(2022, 8, 9, 11, 0, 2), 3100)
        val pph24 = ProductPriceHistory(null, p2, LocalDateTime.of(2022, 8, 10, 17, 0, 2), 3400)
        val pph25 = ProductPriceHistory(null, p2, LocalDateTime.of(2022, 8, 11, 12, 0, 2), 3000)
        val pph26 = ProductPriceHistory(null, p2, LocalDateTime.of(2022, 8, 12, 16, 0, 2), 2990)
        this.productPriceHistoryJpaRepository.saveAll(listOf(pph21, pph22, pph23, pph24, pph25, pph26))

        val m1 = Metric(null, "충청남도 강우량", "API", "http://충청남도_강우량_API_링크", "0 0 0 * *", null, 120.0, true, true)
        val m2 = Metric(null, "최근 3개월간 극심한 가뭄 발생 횟수", "CSV", "http://CSV_LINK", "0 0 0 0 *", null, 3.0, true, true)
        val m3 = Metric(null, "마켓컬리 월별 물류창고 유지비", "API", "http://마켓컬리_월별_물류창고_유지비", "0 0 0 0 *", null, 100.0, true, true)
        val m4 = Metric(null, "테스트 속성 4", "OTHER", "http://테스트_속성_4", null, "objectKey/3", 1.0, false, true);
        val m5 = Metric(null, "테스트 속성 5", "OTHER", "http://테스트_속성_5", null, "objectKey/4", 2.0, true, true);
        this.metricJpaRepository.saveAll(listOf(m1, m2, m3, m4, m5))

        this.metricHistoryJpaRepository.saveAll(listOf(
            MetricHistory(null, m1, LocalDateTime.of(2022, 8, 7, 6, 0, 2), 80.0),
            MetricHistory(null, m1, LocalDateTime.of(2022, 8, 9, 6, 0, 2), 10.0),
            MetricHistory(null, m1, LocalDateTime.of(2022, 8, 9, 6, 0, 2), 0.0),
            MetricHistory(null, m1, LocalDateTime.of(2022, 8, 10, 6, 0, 2), 20.0),
            MetricHistory(null, m1, LocalDateTime.of(2022, 8, 12, 6, 0, 2), 50.0)
        ))

        val pm1 = ProductMetric(null, p1, m1, Importance.VERY_HIGH)
        val pm2 = ProductMetric(null, p1, m3, Importance.HIGH)
        val pm3 = ProductMetric(null, p2, m2, Importance.MEDIUM)
        val pm4 = ProductMetric(null, p2, m3, Importance.VERY_HIGH)
        val pm5 = ProductMetric(null, p1, m5, Importance.VERY_LOW)
        this.productMetricJpaRepository.saveAll(listOf(pm1, pm2, pm3, pm4, pm5))
    }
}