package com.wangdi.util.controlbreak;
/**
 * 初步实现IBreakHandler接口的抽象类
 * 
 * 以忽略（即不发生Break）为原则，实现了第2-16层的判断（isKey[2-16]Break）处理及
 * Break事前事后处理（doPrepareBeforeLevel[2-16]、doBreakAfterLevel[2-16]） 的接口方法。
 * 
 * 实现本类时，最低只需实现doContinue方法和第[1]层的相关方法（isKey1Break、
 * doPrepareBeforeLevel1、doBreakAfterLevel1）。
 * 如需多层的Break操作，Override实现相应接口方法即可。
 * 
 * @version 1.2
 * @author WangDi
 * @date 2016/02/02
 */
public abstract class AbstractBreakHandler<T> implements IBreakHandler<T> {

	@Override
	public abstract boolean isKey1Break(T baseRecord, T nextRecord);

	@Override
	public abstract void doPrepareBeforeLevel1(T firstRecordOfGroup);

	@Override
	public abstract void doContinue(T currRecord);

	@Override
	public abstract void doBreakAfterLevel1(T lastRecordOfGroup);

	@Override
	public boolean isKey2Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey3Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey4Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey5Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey6Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey7Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey8Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey9Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey10Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey11Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey12Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey13Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey14Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey15Break(T baseRecord, T nextRecord) { return false; }
	@Override
	public boolean isKey16Break(T baseRecord, T nextRecord) { return false; }

	@Override
	public void doPrepareBeforeAll() {}

	@Override
	public void doPrepareBeforeLevel2(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel3(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel4(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel5(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel6(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel7(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel8(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel9(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel10(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel11(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel12(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel13(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel14(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel15(T firstRecordOfGroup) { }
	@Override
	public void doPrepareBeforeLevel16(T firstRecordOfGroup) { }

	@Override
	public boolean isSkip(T currRecord) { return false; }

	@Override
	public void doBreakAfterLevel2(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel3(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel4(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel5(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel6(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel7(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel8(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel9(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel10(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel11(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel12(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel13(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel14(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel15(T lastRecordOfGroup) { }
	@Override
	public void doBreakAfterLevel16(T lastRecordOfGroup) { }

	@Override
	public void doBreakAfterAll() { }
}
