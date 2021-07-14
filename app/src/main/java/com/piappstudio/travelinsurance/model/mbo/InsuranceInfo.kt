package com.piappstudio.travelinsurance.model.mbo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InsuranceInfo(

	@field:SerializedName("InsuranceInfo")
	val insuranceInfo: List<InsuranceInfoItem>? = null
) : Parcelable

@Parcelize
data class InsuranceInfoItem(

	@field:SerializedName("GaragesInCity")
	val garagesInCity: Int? = null,

	@field:SerializedName("CoverType")
	val coverType: Int? = null,

	@field:SerializedName("listPlanTextDetails")
	val listPlanTextDetails: List<String?>? = null,

	@field:SerializedName("EmergencyCoverPremium")
	val emergencyCoverPremium: Double? = null,

	@field:SerializedName("IsSelectedIDVMatched")
	val isSelectedIDVMatched: Boolean? = null,

	@field:SerializedName("IsSpecialPrice")
	val isSpecialPrice: Boolean? = null,

	@field:SerializedName("PlanName")
	val planName: String? = null,

	@field:SerializedName("IsLLTP")
	val isLLTP: Boolean? = null,

	@field:SerializedName("IsCorporate")
	val isCorporate: Boolean? = null,

	@field:SerializedName("IsOnline")
	val isOnline: Boolean? = null,

	@field:SerializedName("IsRenewal")
	val isRenewal: Boolean? = null,

	@field:SerializedName("GroupID")
	val groupID: Int? = null,

	@field:SerializedName("Validations")
	val validations: Validations? = null,

	@field:SerializedName("AddOnFilters")
	val addOnFilters: String? = null,

	@field:SerializedName("IsCessApplicable")
	val isCessApplicable: Boolean? = null,

	@field:SerializedName("ODTerm")
	val oDTerm: Int? = null,

	@field:SerializedName("MinimumIdv")
	val minimumIdv: Double? = null,

	@field:SerializedName("IsFireAndTheft")
	val isFireAndTheft: Boolean? = null,

	@field:SerializedName("ImagePath")
	val imagePath: String? = null,

	@field:SerializedName("KMSDriven")
	val kMSDriven: Int? = null,

	@field:SerializedName("IsCache")
	val isCache: Boolean? = null,

	@field:SerializedName("Idv")
	val idv: Double? = null,

	@field:SerializedName("Breakup")
	val breakup: Breakup? = null,

	@field:SerializedName("IrdaPackagePremium")
	val irdaPackagePremium: Double? = null,

	@field:SerializedName("AddonType")
	val addonType: Int? = null,

	@field:SerializedName("IsSelectedExactIDVMatch")
	val isSelectedExactIDVMatch: Boolean? = null,

	@field:SerializedName("Priority")
	val priority: Int? = null,

	@field:SerializedName("PlanId")
	val planId: Int? = null,

	@field:SerializedName("VehicleType")
	val vehicleType: Int? = null,

	@field:SerializedName("IsCorPlan")
	val isCorPlan: Boolean? = null,

	@field:SerializedName("PlanConversionId")
	val planConversionId: Int? = null,

	@field:SerializedName("TPTerm")
	val tPTerm: Int? = null,

	@field:SerializedName("SupplierId")
	val supplierId: Int? = null,

	@field:SerializedName("IsDefaultShow")
	val isDefaultShow: Boolean? = null,

	@field:SerializedName("SupplierName")
	val supplierName: String? = null,

	@field:SerializedName("RSAPremium")
	val rSAPremium: Double? = null,

	@field:SerializedName("FinalPremium")
	val finalPremium: Double? = null,

	@field:SerializedName("QuoteSource")
	val quoteSource: Int? = null,

	@field:SerializedName("FeatureIdList")
	val featureIdList: List<FeatureIdListItem?>? = null,

	@field:SerializedName("MaximumIdv")
	val maximumIdv: Double? = null,

	@field:SerializedName("PackagePremium")
	val packagePremium: Double? = null
) : Parcelable

@Parcelize
data class AddOnFilters(

	@field:SerializedName("IsCOC")
	val isCOC: Boolean? = null,

	@field:SerializedName("IsZD")
	val isZD: Boolean? = null,

	@field:SerializedName("IsEP")
	val isEP: Boolean? = null,

	@field:SerializedName("IsINPC")
	val isINPC: Boolean? = null,

	@field:SerializedName("IsRSA")
	val isRSA: Boolean? = null,

	@field:SerializedName("IsDAC")
	val isDAC: Boolean? = null,

	@field:SerializedName("IsKLR")
	val isKLR: Boolean? = null,

	@field:SerializedName("IsLPB")
	val isLPB: Boolean? = null,

	@field:SerializedName("IsTC")
	val isTC: Boolean? = null
) : Parcelable

@Parcelize
data class Breakup(

	@field:SerializedName("AntiTheftDiscount")
	val antiTheftDiscount: Double? = null,

	@field:SerializedName("AgeDiscount")
	val ageDiscount: Double? = null,

	@field:SerializedName("VoluntaryDiscount")
	val voluntaryDiscount: Double? = null,

	@field:SerializedName("ZeroDepPremium")
	val zeroDepPremium: Double? = null,

	@field:SerializedName("PAForUnnamedPassengerPremium")
	val pAForUnnamedPassengerPremium: Double? = null,

	@field:SerializedName("NetCoverPremium")
	val netCoverPremium: Double? = null,

	@field:SerializedName("CoverType")
	val coverType: Int? = null,

	@field:SerializedName("CompulsaryPACoverForOwnerDriverPremium")
	val compulsaryPACoverForOwnerDriverPremium: Double? = null,

	@field:SerializedName("PaidDriverPremium")
	val paidDriverPremium: Double? = null,

	@field:SerializedName("IsOnline")
	val isOnline: Boolean? = null,

	@field:SerializedName("NetAddonPremium")
	val netAddonPremium: Double? = null,

	@field:SerializedName("WindShieldPremium")
	val windShieldPremium: Double? = null,

	@field:SerializedName("NetDiscount")
	val netDiscount: Double? = null,

	@field:SerializedName("FireAndTheftPremium")
	val fireAndTheftPremium: Double? = null,

	@field:SerializedName("CostOfConsumablesPremium")
	val costOfConsumablesPremium: Double? = null,

	@field:SerializedName("KeyReplacementPremium")
	val keyReplacementPremium: Double? = null,

	@field:SerializedName("AamDiscount")
	val aamDiscount: Double? = null,

	@field:SerializedName("LegalLiabilityToPaidDriverPremium")
	val legalLiabilityToPaidDriverPremium: Double? = null,

	@field:SerializedName("AddOnSum")
	val addOnSum: Double? = null,

	@field:SerializedName("TPPDLiabilityPremium")
	val tPPDLiabilityPremium: Double? = null,

	@field:SerializedName("BiFuelKitPremium")
	val biFuelKitPremium: Double? = null,

	@field:SerializedName("NetLiabilityPremium")
	val netLiabilityPremium: Double? = null,

	@field:SerializedName("RimPremium")
	val rimPremium: Double? = null,

	@field:SerializedName("FinalPremium")
	val finalPremium: Double? = null,

	@field:SerializedName("BasicPremium")
	val basicPremium: Double? = null,

	@field:SerializedName("BasePremium")
	val basePremium: Double? = null,

	@field:SerializedName("ProfessionDiscount")
	val professionDiscount: Double? = null,

	@field:SerializedName("BaseDistLiabilitySum")
	val baseDistLiabilitySum: Double? = null,

	@field:SerializedName("BiFuelKitLiabilityPremium")
	val biFuelKitLiabilityPremium: Double? = null,

	@field:SerializedName("DailyAllowancePremium")
	val dailyAllowancePremium: Double? = null,

	@field:SerializedName("RsaPremium")
	val rsaPremium: Double? = null,

	@field:SerializedName("PersonelAssistancePremium")
	val personelAssistancePremium: Double? = null,

	@field:SerializedName("TCPremium")
	val tCPremium: Double? = null,

	@field:SerializedName("IsLLTP")
	val isLLTP: Int? = null,

	@field:SerializedName("EngineProtectorPremium")
	val engineProtectorPremium: Double? = null,

	@field:SerializedName("AddOnFilters")
	val addOnFilters: String? = null,

	@field:SerializedName("Loading")
	val loading: Double? = null,

	@field:SerializedName("ServiceTax")
	val serviceTax: Double? = null,

	@field:SerializedName("NonElecAccessoriesPremium")
	val nonElecAccessoriesPremium: Double? = null,

	@field:SerializedName("LossOfPersonalBelongingPremium")
	val lossOfPersonalBelongingPremium: Double? = null,

	@field:SerializedName("MDPremium")
	val mDPremium: Double? = null,

	@field:SerializedName("NcbDiscount")
	val ncbDiscount: Double? = null,

	@field:SerializedName("PlanId")
	val planId: Int? = null,

	@field:SerializedName("IsCorPlan")
	val isCorPlan: Boolean? = null,

	@field:SerializedName("ElecAccessoriesPremium")
	val elecAccessoriesPremium: Double? = null,

	@field:SerializedName("PAPremium")
	val pAPremium: Double? = null,

	@field:SerializedName("ServiceTaxMultiplier")
	val serviceTaxMultiplier: Double? = null,

	@field:SerializedName("InsurerDiscount")
	val insurerDiscount: Double? = null,

	@field:SerializedName("InvoicePriceCoverPremium")
	val invoicePriceCoverPremium: Double? = null,

	@field:SerializedName("EmergencyTransportandHotelPremium")
	val emergencyTransportandHotelPremium: Double? = null,

	@field:SerializedName("ProposalNumber")
	val proposalNumber: String? = null,

	@field:SerializedName("NcbProtectorPremium")
	val ncbProtectorPremium: Double? = null
) : Parcelable

@Parcelize
data class FeatureIdListItem(

	@field:SerializedName("FeatureId")
	val featureId: Int? = null,

	@field:SerializedName("FeatureSufix")
	val featureSufix: String? = null
) : Parcelable

@Parcelize
data class Validations(

	@field:SerializedName("ShowYourCurrentInsurer")
	val showYourCurrentInsurer: Boolean? = null,

	@field:SerializedName("ShowCashlessGarages")
	val showCashlessGarages: Boolean? = null,

	@field:SerializedName("ShowSlashedPricing")
	val showSlashedPricing: Boolean? = null,

	@field:SerializedName("ShowFreeText")
	val showFreeText: Boolean? = null,

	@field:SerializedName("ShowPolicyDetails")
	val showPolicyDetails: Boolean? = null,

	@field:SerializedName("ShowIncludedText")
	val showIncludedText: Boolean? = null,

	@field:SerializedName("ShowBreakup")
	val showBreakup: Boolean? = null,

	@field:SerializedName("Error")
	val error: Boolean? = null,

	@field:SerializedName("ShowBenefits")
	val showBenefits: Boolean? = null,

	@field:SerializedName("ShowClaimAdvantage")
	val showClaimAdvantage: Boolean? = null
) : Parcelable
