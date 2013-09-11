package ui.process;

public class PanelProcess1 extends PanelProcess {
	protected void initValues() {
		pnlTabParam.getSpinMaxPrimerSize().setValue(22);
		pnlTabParam.getSpinOptPrimerSize().setValue(20);
		pnlTabParam.getSpinMinPrimerSize().setValue(18);

		pnlTabParam.getSpinMaxProductSize().setValue(450);
		pnlTabParam.getSpinMinProductSize().setValue(300);

		pnlTabParam.getSpinMaxTm().setValue(57d);
		pnlTabParam.getSpinOptTm().setValue(60d);
		pnlTabParam.getSpinMinTm().setValue(63d);

		pnlTabParam.getSpinMaxGC().setValue(60d);
		pnlTabParam.getSpinOptGC().setValue(50d);
		pnlTabParam.getSpinMinGC().setValue(40d);

		spinTmDiff.setValue(3d);
		spinLastSubSeqSize.setValue(100);
		
		pnlTabThermo.getSpinAnyMaxSelfComp().setValue(6d);
		pnlTabThermo.getSpinEndMaxSelfComp().setValue(3d);
		pnlTabThermo.getSpinAnyMaxPairComp().setValue(6d);
		pnlTabThermo.getSpinEndMaxPairComp().setValue(3d);
		pnlTabThermo.getSpinMaxPrimerHairpin().setValue(40d);
	}
}
