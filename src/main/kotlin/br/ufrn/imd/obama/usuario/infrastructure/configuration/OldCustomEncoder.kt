package br.ufrn.imd.obama.usuario.infrastructure.configuration

import java.security.MessageDigest
import java.util.*

class OldCustomEncoder {

    companion object {
        private val md: MessageDigest = MessageDigest.getInstance("MD5");
    }

    private fun hexCodes(text: ByteArray): CharArray {
        val hexOutput = CharArray(text.size * 2)
        var hexString: String
        for (i in text.indices) {
            hexString = "00" + Integer.toHexString(text[i].toInt())
            hexString.uppercase(Locale.getDefault()).toCharArray(
                hexOutput, i * 2, hexString.length - 2,
                hexString.length
            )
        }
        return hexOutput
    }

    fun encode(rawPassword: CharSequence?): String {
        if (!rawPassword.isNullOrBlank()) {
            return String(hexCodes(md.digest(rawPassword.toString().toByteArray())))
        }
        return ""
    }

    fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return encode(rawPassword) == encodedPassword
    }
}
