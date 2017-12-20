package com.xwtiger.devrescollect.study.androidapi.img;

/**
 *
 *
 *ColorFilter主要用来处理颜色，这里将讲解它的三个子类，
 * ColorMatrixColorFilter,
 LightingColorFilter以及PorterDuffColorFilter
 * 这个类主要是使用matrix也就是矩阵对颜色做运算，矩阵的形态如下：

 a[0] a[1] a[2] a[3] a[4]
 a[5] a[6] a[7] a[8] a[9]
 a[10] a[11] a[12] a[13] a[14]
 a[15] a[16] a[17] a[18] a[19]

 RGB和Alpha的终值计算方法如下：
 Red通道终值= a[0] * srcR + a[1] * srcG + a[2] * srcB + a[3] * srcA + a[4]
 Green通道终值= a[5] * srcR + a[6] * srcG + a[7] * srcB + a[8] * srcA + a[9]
 Blue通道终值= a[10] * srcR + a[11] * srcG + a[12] * srcB + a[13] * srcA + a[14]
 Alpha通道终值= a[15]*srcR+a[16]*srcG + a[17] * srcB + a[18] * srcA + a[19]

 备注：
 srcR为原图Red通道值，srcG为原图Green通道值，srcB为原图Blue通道值，srcA为原图Alpha通道值。
 每个通道的源值和终值都在0到255的范围内。即使计算结果大于255或小于0，值都将被限制在0到255的范围内。

 *DEMO 参见TestColorFilterView
 *
 */

public class ColorFilterStudy {


}
