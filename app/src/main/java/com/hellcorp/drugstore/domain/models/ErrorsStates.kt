package com.hellcorp.drugstore.domain.models

import com.hellcorp.drugstore.R


enum class ErrorsStates(val messageResource: Int, val imageResource: Int) {
    EMPTY(-1, R.drawable.ic_empty_search),
    NO_INTERNET(R.string.no_internet, R.drawable.ic_no_internet),
    NOT_FOUND_LIST(R.string.no_drug_list, R.drawable.ic_not_found),
    NOT_FOUND_INFO(R.string.no_drug_info, R.drawable.ic_not_found),
    SERVER_ERROR(R.string.server_error, R.drawable.ic_server_error),
}
