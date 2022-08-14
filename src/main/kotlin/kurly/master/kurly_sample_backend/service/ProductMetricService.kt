package kurly.master.kurly_sample_backend.service

import kurly.master.kurly_sample_backend.controller.AddMetricDto
import kurly.master.kurly_sample_backend.controller.DeleteMetricDto
import kurly.master.kurly_sample_backend.controller.ModifyMetricImportanceDto
import kurly.master.kurly_sample_backend.repository.Importance
import kurly.master.kurly_sample_backend.repository.MetricByProductDto
import kurly.master.kurly_sample_backend.repository.ProductByMetricDto
import kurly.master.kurly_sample_backend.repository.ProductMetricRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductMetricService(
    private val productMetricRepository: ProductMetricRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getMetricsOfProduct(productId: Long): List<MetricByProductDto> {
        return this.productMetricRepository.getMetricsOfProduct(productId)
    }

    fun getProductsOfMetric(metricId: Long): List<ProductByMetricDto> {
        return this.productMetricRepository.getProductsOfMetric(metricId)
    }

    fun addMetricsToProduct(addMetricDto: AddMetricDto): Map<Long, Boolean> {
        return addMetricDto.metricIds.associateWith {
            this.productMetricRepository.addMetricToProduct(addMetricDto.productId, it)
        }
    }

    fun deleteMetricsOfProduct(deleteMetricDto: DeleteMetricDto): Map<Long, Boolean> {
        return deleteMetricDto.metricIds.associateWith {
            this.productMetricRepository.deleteMetricToProduct(deleteMetricDto.productId, it)
        }
    }

    fun modifyMetricImportanceOfProduct(modifyMetricImportanceDto: ModifyMetricImportanceDto): Boolean {
        val importance = when(modifyMetricImportanceDto.importance){
            1 -> Importance.VERY_HIGH
            2 -> Importance.HIGH
            3 -> Importance.MEDIUM
            4 -> Importance.LOW
            5 -> Importance.VERY_LOW
            else -> throw IllegalStateException("중요도가 올바른 값이 아닙니다!")
        }
        return this.productMetricRepository.modifyMetricOfProduct(
            modifyMetricImportanceDto.productId,
            modifyMetricImportanceDto.metricId,
            importance
        )

    }
}