package br.com.williamandradedev.pocmongo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PocmongoApplication

fun main(args: Array<String>) {
	runApplication<PocmongoApplication>(*args)
}
