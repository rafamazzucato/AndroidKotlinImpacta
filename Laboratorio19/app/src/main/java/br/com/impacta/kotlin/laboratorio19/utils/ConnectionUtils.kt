package br.com.impacta.kotlin.laboratorio19.utils

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object Constantes {
    val web_service = "http://www.nmsystems.com.br/testecarga.php"
}

fun comunicacao(urlEnd: String, params: String): String {
    val sb = StringBuilder()
    val url: URL
    var conn: HttpURLConnection? = null
    try {
        url = URL(urlEnd)
        conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = "POST"
        conn.doInput = true
        conn.doOutput = true

        //
        //conn.setConnectTimeout(60000);
        //conn.setReadTimeout(60000);
        //

        val parametrosFormatados = StringBuilder()
        parametrosFormatados.append(URLEncoder.encode("json", "UTF-8"))
        parametrosFormatados.append("=")
        parametrosFormatados.append(URLEncoder.encode(params, "UTF-8"))

        //
        // Envio de Parametros
        val os = conn.outputStream
        val writer = BufferedWriter(
            OutputStreamWriter(os, "UTF-8")
        )

        writer.write(parametrosFormatados.toString())
        writer.flush()
        writer.close()

        //os.close();
        //
        // Ler as informacoes enviados pelo Servidor
        sb.append(readStream(conn.inputStream))
    } catch (e: Exception) {
        sb.append(e.toString())
    } finally {
        conn?.disconnect()
    }
    return sb.toString()
}
private fun readStream(inputStream: InputStream): String {
    var reader: Reader? = null
    val writer = StringWriter()
    val buffer = CharArray(1024)
    try {
        reader = BufferedReader(
            InputStreamReader(inputStream, "UTF-8")
        )
        var n: Int = 0
        while (true) {
            n = reader.read(buffer)
            if (n != -1) {
                writer.write(buffer, 0, n)
            } else {
                break
            }
        }
    } catch (e: Exception) {
    } finally {
        if (reader != null) {
            try {
                reader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
//
    return writer.toString()
}