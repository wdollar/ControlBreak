package com.wangdi.util.controlbreak;


/**
 * Control Break处理用Handler接口(Java 1.8版)
 * 
 *  1. 第[N]层Break判断处理：isKey[N]Break(T 基准记录, T 后一条记录)
 *     判断后一条记录第[N]层的Key与基准记录是否一致（即第[N]层是否发生Break）。
 *       【返回值】
 *          true：发生Break（与基准记录不一致）
 *          false：未发生Break（与基准记录一致）
 * 
 *  2. 前期准备方法：doPrepareBeforeAll()
 *      在所有其他处理前执行(无可用记录时也会执行)
 * 
 *  3. 第[N]层Break前执行的处理：doPrepareBeforeLevel[N](T 本层第一条记录)
 *      (无可用记录时不会执行)
 * 
 *  4. 当前记录是否需要跳过的判断处理：isSkip(T 当前记录)
 *      【返回值】
 *          true：跳过当前记录（即完全忽略当前记录，使之不对其他doXXXXXXXX接口的处理产生影响）
 *          false：不跳过当前记录（即当前记录为各种doXXXXXXXX接口的处理对象）
 * 
 *  5. 当前记录的处理：doContinue(T 当前记录)
 *      (无可用记录时不会执行)
 * 
 *  6. 第[N]层Break后执行的处理：doBreakAfterLevel[N](T 本层最后一条记录)
 *      (无可用记录时不会执行)
 * 
 *  7. 终了处理方法：doBreakAfterAll()
 *      在所有其他处理后执行(无可用记录时也会执行)
 * 
 *  8. 结果返回方法：getResult(处理参数)
 *      根据参数返回ControlBreak处理结果。
 * 
 * ※注1：
 *   以忽略（即不发生Break）为原则，default实现了第2-16层的判断（isKey[2-16]Break）处理及
 *   Break事前事后处理（doPrepareBeforeLevel[2-16]、doBreakAfterLevel[2-16]） 的接口方法。
 * 
 * ※注2：
 *   实现本接口时，最低只需实现doContinue方法和第[1]层的相关方法（isKey1Break、
 *   doPrepareBeforeLevel1、doBreakAfterLevel1）。
 *   如需多层的Break操作，Override实现相应接口方法即可。
 * 
 * @version 1.2
 * @author WangDi
 * @date 2016/02/02
 */
public interface IBreakHandler<T> {
	public boolean isKey1Break(T baseRecord, T nextRecord);

	public void doPrepareBeforeLevel1(T firstRecordOfGroup);

	public void doContinue(T currRecord);

	public void doBreakAfterLevel1(T lastRecordOfGroup);

	default public Object getResult(Object param) { return new Object(); }

	default public boolean isKey2Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey3Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey4Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey5Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey6Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey7Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey8Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey9Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey10Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey11Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey12Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey13Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey14Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey15Break(T baseRecord, T nextRecord) { return false; }
	default public boolean isKey16Break(T baseRecord, T nextRecord) { return false; }

	default public void doPrepareBeforeAll() { }

	default public void doPrepareBeforeLevel2(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel3(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel4(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel5(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel6(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel7(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel8(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel9(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel10(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel11(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel12(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel13(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel14(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel15(T firstRecordOfGroup) { }
	default public void doPrepareBeforeLevel16(T firstRecordOfGroup) { }

	default public boolean isSkip(T currRecord) { return false; }

	default public void doBreakAfterLevel2(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel3(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel4(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel5(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel6(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel7(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel8(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel9(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel10(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel11(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel12(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel13(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel14(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel15(T lastRecordOfGroup) { }
	default public void doBreakAfterLevel16(T lastRecordOfGroup) { }

	default public void doBreakAfterAll() { }
}
