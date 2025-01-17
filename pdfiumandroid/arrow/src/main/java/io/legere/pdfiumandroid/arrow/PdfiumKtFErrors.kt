package io.legere.pdfiumandroid.arrow

sealed class PdfiumKtFErrors {
    data class RuntimeException(
        val message: String,
    ) : PdfiumKtFErrors()

    data class AlreadyClosed(
        val message: String,
    ) : PdfiumKtFErrors()

    data class AlreadyLocked(
        val message: String,
    ) : PdfiumKtFErrors()
}

fun exceptionToPdfiumKtFError(e: Throwable): PdfiumKtFErrors =
    if (e is IllegalStateException && e.message?.contains("Already closed") == true) {
        PdfiumKtFErrors.AlreadyClosed(e.message ?: "Unknown error")
    } else {
        PdfiumKtFErrors.RuntimeException(e.message ?: "Unknown error")
    }
