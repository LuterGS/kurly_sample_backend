package kurly.master.kurly_sample_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.ZoneId
import java.util.TimeZone

@SpringBootApplication
class KurlySampleBackendApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")))

    runApplication<KurlySampleBackendApplication>(*args)
}
