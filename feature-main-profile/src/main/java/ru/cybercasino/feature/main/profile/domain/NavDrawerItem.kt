package ru.cybercasino.feature.main.profile.domain

import ru.cybercasino.ui.R

sealed class NavDrawerItem(var route: String, var icon: Int, var titleId: Int) {
    object RealAccount : NavDrawerItem("real_account", R.drawable.ic_real_account, R.string.real_account_label)
    object BonusAccount : NavDrawerItem("bonus_account", R.drawable.ic_bonus_account, R.string.bonus_account_label)
    object ExperienceGrade : NavDrawerItem("experience_grade", R.drawable.ic_experience_grade, R.string.experience_grade_label)
    object FreeSpins : NavDrawerItem("free_spins", R.drawable.ic_free_spins, R.string.free_spins_label)

    object UserProfile : NavDrawerItem("real_account", R.drawable.ic_user_profile, R.string.user_profile_label)
    object Account : NavDrawerItem("real_account", R.drawable.ic_account, R.string.account_label)
    object Deposit : NavDrawerItem("real_account", R.drawable.ic_deposit, R.string.deposit_label)
    object FundsWithdraw : NavDrawerItem("real_account", R.drawable.ic_funds_withdraw, R.string.funds_withdraw_label)
    object MoneyTransferHistory : NavDrawerItem("money_transfer_history", R.drawable.ic_star, R.string.money_transfer_history)
    object YourBids : NavDrawerItem("your_bonuses", R.drawable.ic_circled_star, R.string.your_bonuses)
    object BidsHistory : NavDrawerItem("bonuses_history", R.drawable.ic_back_to_time, R.string.bonuses_history)
    object Documents : NavDrawerItem("documents", R.drawable.ic_documents, R.string.documents)
    object Messages : NavDrawerItem("messages", R.drawable.ic_messages, R.string.messages)
    object Tournaments : NavDrawerItem("tournaments", R.drawable.ic_tournaments, R.string.tournaments)
}
