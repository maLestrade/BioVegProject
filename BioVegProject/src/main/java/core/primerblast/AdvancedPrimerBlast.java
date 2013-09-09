package core.primerblast;

import java.io.UnsupportedEncodingException;

import apollo.analysis.RemotePrimerBlastNCBI;

/**
 * Class to use primerblast
 * Inherits from apollo program
 * @author pidupuis, gcornut
 *
 */
public class AdvancedPrimerBlast extends RemotePrimerBlastNCBI {

	/**
	 * opts : AdvancedPrimerBlastOptions
	 * Variable which define the primer blast options
	 */
	private AdvancedPrimerBlastOptions opts;

	/**
	 * Constructor using fields
	 * @param opts : AdvancedPrimerBlastOptions
	 */
	public AdvancedPrimerBlast(AdvancedPrimerBlastOptions opts) {
		super(opts);
		this.opts = opts;
	}

	@Override
	/**
	 * Method which process the options
	 * @param buf : StringBuilder
	 * @throws UnsupportedEncodingException
	 */
	public void processOptions(StringBuilder buf) throws UnsupportedEncodingException {
		buf.append("PRIMER5_START=" + convertOptionToString(opts.getPrimer5Start()) + "&");
		buf.append("PRIMER5_END=" + convertOptionToString(opts.getPrimer5End()) + "&");
		buf.append("PRIMER3_START=" + convertOptionToString(opts.getPrimer3Start()) + "&");
		buf.append("PRIMER3_END=" + convertOptionToString(opts.getPrimer3End()) + "&");
		buf.append("PRIMER_LEFT_INPUT=" + convertOptionToString(opts.getPrimerLeftInput()) + "&");
		buf.append("PRIMER_RIGHT_INPUT=" + convertOptionToString(opts.getPrimerRightInput()) + "&");
		buf.append("PRIMER_PRODUCT_MIN=" + convertOptionToString(opts.getPrimerProductMin()) + "&");
		buf.append("PRIMER_PRODUCT_MAX=" + convertOptionToString(opts.getPrimerProductMax()) + "&");
		buf.append("PRIMER_NUM_RETURN=" + convertOptionToString(opts.getPrimerNumReturn()) + "&");
		buf.append("PRIMER_MIN_TM=" + convertOptionToString(opts.getPrimerMinTm()) + "&");
		buf.append("PRIMER_OPT_TM=" + convertOptionToString(opts.getPrimerOptTm()) + "&");
		buf.append("PRIMER_MAX_TM=" + convertOptionToString(opts.getPrimerMaxTm()) + "&");
		buf.append("PRIMER_MAX_DIFF_TM=" + convertOptionToString(opts.getPrimerMaxDiffTm()) + "&");
		buf.append("SEARCH_SPECIFIC_PRIMER=" + (opts.isSearchSpecificPrimer() ? "on" : "off") + "&");
		buf.append("ORGANISM=" + convertOptionToString(opts.getOrganism()) + "&");
		buf.append("PRIMER_SPECIFICITY_DATABASE=" + convertOptionToString(opts.getPrimerSpecificityDatabase().toCGIParameter()) + "&");
		buf.append("TOTAL_PRIMER_SPECIFICITY_MISMATCH=" + convertOptionToString(opts.getTotalPrimerSpecificityMismatch()) + "&");
		buf.append("PRIMER_3END_SPECIFICITY_MISMATCH=" + convertOptionToString(opts.getPrimer3endSpecificityMismatch()) + "&");
		buf.append("MISMATCH_REGION_LENGTH=" + convertOptionToString(opts.getMismatchRegionLength()) + "&");
		buf.append("PRODUCT_SIZE_DEVIATION=" + convertOptionToString(opts.getProductSizeDeviation()) + "&");
		buf.append("PRIMER_MIN_SIZE=" + convertOptionToString(opts.getPrimerSizeMin()) + "&");
		buf.append("PRIMER_OPT_SIZE=" + convertOptionToString(opts.getPrimerSizeOpt()) + "&");
		buf.append("PRIMER_MAX_SIZE=" + convertOptionToString(opts.getPrimerSizeMax()) + "&");
		buf.append("PRIMER_MIN_GC=" + convertOptionToString(opts.getGCMin()) + "&");
		buf.append("PRIMER_MAX_GC=" + convertOptionToString(opts.getGCMax()) + "&");
		
		
	}
}
