package com.example.jennifer.mygooglemap;

/**
 * Created by Jennifer on 2015/11/21.
 */
public class Camera {
    public static int CameraLocationNo=207;
    public static double CameraLocation[][] = {
            {121.454681,25.159568,121.453133,25.160907,7,50},
            {121.452512,25.162512,121.451023,25.163940,6,50},
            {121.459411,25.154656,121.458912,25.156691,6,50},
            {121.453583,25.107115,121.454686,25.108140,5,50},
            {121.452875,25.180743,0,0,7,70},
            {121.452875,25.180743,0,0,3,70},
            {121.460809,25.169100,0,0,6,70},
            {121.456254,25.161575,0,0,6,50},
            {121.456748,25.176018,0,0,2,70},
            {121.456464,25.176382,0,0,6,70},
            {121.459362,25.153196,0,0,2,50},
            {121.500527,25.043898,0,0,6,60},
            {121.508387,25.079056,0,0,-1,60},
            {121.507414,25.082469,0,0,-1,50},
            {121.480009,25.122541,0,0,4,70},
            {121.477420,25.122469,0,0,0,70},
            {121.499001,25.105896,0,0,2,80},
            {121.498756,25.107963,0,0,6,80},
            {121.465140,25.028311,0,0,6,60},
            {121.465468,25.028428,0,0,2,60},
            {121.537016,24.965991,0,0,-1,60},
            {121.530318,24.971383,0,0,-1,60},
            {121.448399,25.092054,0,0,6,80},
            {121.511965,25.104991,0,0,6,60},
            {121.512119,25.104093,0,0,2,60},
            {121.506651,25.021618,0,0,5,60},
            {121.500462,25.027896,0,0,6,50},
            {121.482123,25.009047,0,0,3,70},
            {121.505826,24.993433,0,0,4,70},
            {121.491795,24.993995,0,0,0,70},
            {121.471125,25.07557,0,0,4,100},
            { 121.795905,24.849086,0,0,-1,50 },
            { 121.4298,25.1589,0,0,-1,-1 },
            { 120.6232058,24.1499723,0,0,-1,-1 },
            { 120.68504,24.16546,0,0,-1,-1 },
            { 120.68548,24.17238,0,0,-1,-1 },
            { 120.943136,24.583449,0,0,-1,-1 },
            { 121.591417,24.999041,0,0,-1,-1 },
            { 121.725088,25.107702,0,0,-1,100 },
            { 121.536863,25.073051,0,0,-1,100 },
            { 121.466465,25.075891,0,0,-1,100 },
            { 121.167409,24.91772,0,0,-1,100 },
            { 121.082152,24.88472,0,0,-1,100 },
            { 120.969319,24.736586,0,0,-1,100 },
            { 120.860447,24.591946,0,0,-1,100 },
            { 120.852995,24.571255,0,0,-1,100 },
            { 120.746455,24.37083,0,0,-1,100 },
            { 120.693195,24.274199,0,0,-1,110 },
            { 120.67391,24.214793,0,0,-1,110 },
            { 120.59834,24.11271,0,0,-1,110 },
            { 120.523776,24.053078,0,0,-1,110 },
            { 120.48427,23.84565,0,0,-1,110 },
            { 120.476086,23.712355,0,0,-1,110 },
            { 120.434351,23.597476,0,0,-1,110 },
            { 120.405818,23.527653,0,0,-1,110 },
            { 120.355248,23.397737,0,0,-1,110 },
            { 120.306842,23.334305,0,0,-1,110 },
            { 120.267327,23.27464,0,0,-1,110 },
            { 120.23653,23.20419,0,0,-1,110 },
            { 120.231495,23.164121,0,0,-1,110 },
            { 120.251739,23.082616,0,0,-1,110 },
            { 120.249296,23.026246,0,0,-1,110 },
            { 120.284681,22.867977,0,0,-1,110 },
            { 120.33219,22.758501,0,0,-1,110 },
            { 121.725261,25.107657,0,0,-1,100 },
            { 121.680591,25.085822,0,0,-1,100 },
            { 121.616395,25.065895,0,0,-1,100 },
            { 121.26481,25.011853,0,0,-1,100 },
            { 121.195021,24.946716,0,0,-1,100 },
            { 121.045897,24.876628,0,0,-1,100 },
            { 120.945308,24.72683,0,0,-1,100 },
            { 120.788042,24.462056,0,0,-1,100 },
            { 120.670757,24.212472,0,0,-1,110 },
            { 120.484809,23.831926,0,0,-1,110 },
            { 120.404042,23.51677,0,0,-1,110 },
            { 120.428493,23.581108,0,0,-1,110 },
            { 120.355291,23.387622,0,0,-1,110 },
            { 120.271282,23.27852,0,0,-1,110 },
            { 120.236717,23.204872,0,0,-1,110 },
            { 120.234682,23.137455,0,0,-1,110 },
            { 120.249371,23.011912,0,0,-1,110 },
            { 120.260136,22.918659,0,0,-1,110 },
            { 120.270433,22.896294,0,0,-1,110 },
            { 120.332099,22.759115,0,0,-1,110 },
            { 121.601445,25.064278,0,0,-1,100 },
            { 121.471459,25.075245,0,0,-1,100 },
            { 121.601697,25.063947,0,0,-1,100 },
            { 121.217198,25.049834,0,0,-1,100 },
            { 121.215812,25.052505,0,0,-1,100 },
            { 121.218536,25.046611,0,0,-1,100 },
            { 121.283248,25.002049,0,0,-1,100 },
            { 121.282976,24.995671,0,0,-1,100 },
            { 121.457271,24.975222,0,0,-1,100 },
            { 121.25546,24.881098,0,0,-1,110 },
            { 121.149866,24.807202,0,0,-1,110 },
            { 120.925391,24.746012,0,0,-1,110 },
            { 120.893935,24.715066,0,0,-1,110 },
            { 120.67839,24.453048,0,0,-1,110 },
            { 120.590927,24.229564,0,0,-1,110 },
            { 120.63102,23.774384,0,0,-1,110 },
            { 120.506081,23.572687,0,0,-1,110 },
            { 120.333687,22.971913,0,0,-1,110 },
            { 120.441592,22.76922,0,0,-1,110 },
            { 120.524814,22.508149,0,0,-1,110 },
            { 121.625792,25.046878,0,0,-1,90 },
            { 121.570831,24.976095,0,0,-1,90 },
            { 121.465517,24.977515,0,0,-1,100 },
            { 121.304277,24.924304,0,0,-1,110 },
            { 120.779073,24.591296,0,0,-1,110 },
            { 120.602092,24.293697,0,0,-1,110 },
            { 120.638645,24.071049,0,0,-1,110 },
            { 120.650424,23.999997,0,0,-1,110 },
            { 120.690258,23.777216,0,0,-1,110 },
            { 120.60038,23.694983,0,0,-1,110 },
            { 120.3556,23.204341,0,0,-1,110 },
            { 120.329009,23.087045,0,0,-1,110 },
            { 120.382939,22.849311,0,0,-1,110 },
            { 120.471479,22.758016,0,0,-1,110 },
            { 121.579177,25.004793,0,0,-1,80 },
            { 120.627791,24.305094,0,0,-1,100 },
            { 121.701017,24.949433,0,0,-1,80 },
            { 121.732855,24.916127,0,0,-1,90 },
            { 121.753732,24.899525,0,0,-1,90 },
            { 121.778895,24.869598,0,0,-1,90 },
            { 121.805564,24.63744,0,0,-1,90 },
            { 121.652551,24.994559,0,0,-1,90 },
            { 121.733312,24.91648,0,0,-1,90 },
            { 121.753488,24.900517,0,0,-1,90 },
            { 121.779333,24.869922,0,0,-1,90 },
            { 121.780957,24.791564,0,0,-1,90 },
            { 120.191133,23.0596,0,0,-1,70 },
            { 120.209621,23.089608,0,0,-1,40 },
            { 120.406754,22.770909,0,0,-1,100 },
            { 120.386905,22.760333,0,0,-1,100 },
            { 121.497167,25.019113,0,0,2,50 },
            { 121.497167,25.019113,0,0,6,50 },
            { 121.537903,25.027829,0,0,2,50 },
            { 121.537181,25.062058,0,0,2,50 },
            { 121.536713,25.052643,0,0,6,70 },
            { 121.536961,25.048107,0,0,2,50 },
            { 121.537617,25.026159,0,0,6,70 },
            { 121.542342,25.044673,0,0,0,80 },
            { 121.552223,25.044504,0,0,4,80 },
            { 121.446687,25.192171,0,0,2,70 },
            { 121.447463,25.198276,0,0,1,60 },
            { 121.449042,25.206077,0,0,2,50 },
            { 121.450601,25.213422,0,0,2,60 },
            { 121.449982,25.218647,0,0,6,60 },
            { 121.451829,25.224085,0,0,6,50 },
            { 121.381503,25.140298,0,0,1,50 },
            { 121.390496,25.142065,0,0,4,50 },
            { 121.407161,25.150943,0,0,1,50 },
            { 121.423195,25.158631,0,0,1,50 },
            { 121.429117,25.159012,0,0,0,50 },
            { 121.449959,25.135975,0,0,2,50 },
            { 121.451461,25.130353,0,0,2,50 },
            { 121.451451,25.132119,0,0,6,50 },
            { 121.454455,25.123418,0,0,3,50 },
            { 121.457796,25.115202,0,0,2,50 },
            { 121.456581,25.238032,0,0,6,60 },
            { 121.459444,25.246643,0,0,1,70 },
            { 121.470001,25.251064,0,0,1,60 },
            { 121.470416,25.251386,0,0,5,60 },
            { 121.482502,25.263718,0,0,4,70 },
            { 121.492534,25.260159,0,0,0,70 },
            { 121.501349,25.261496,0,0,4,70 },
            { 121.542859,25.288674,0,0,7,70 },
            { 121.553707,25.291776,0,0,5,60 },
            { 121.564382,25.291014,0,0,0,60 },
            { 121.568199,25.293414,0,0,6,50 },
            { 121.581975,25.295202,0,0,7,70 },
            {121.5257783,24.98401551,0,0,5,60},
            {121.4923717,25.02603114,0,0,1,50},
            {121.4972363,25.01938433,0,0,1,50},
            {121.5336281,25.03044083,0,0,6,50},
            {121.5269105,25.02685333,0,0,4,50},
            {121.5159101,25.02759924,0,0,1,50},
            {121.5160372,25.02849845,0,0,6,50},
            {121.5085580,25.04293327,0,0,1,50},
            {121.432218,25.174787,0,0,4,50},
            {121.435748,25.173384,0,0,4,40},
            {121.518908,25.092690,0,0,5,50},
            {121.511256,25.087041,0,0,1,50},
            {121.455654,25.121041,0,0,6,50},
            {121.486065,25.262318,0,0,0,70},
            {121.519703,25.282264,0,0,1,60},
            {121.496702,25.054238,0,0,3,50},
            {121.502474,25.033349,0,0,4,50},
            {121.507450,25.039023,0,0,2,50},
            {121.507484,25.040061,0,0,6,50},
            {121.523437,25.044851,0,0,4,50},
            {121.525498,24.975241,0,0,3,60},
            {121.4428668,25.15064222,0,0,7,50},
            {121.4408452,25.15286812,0,0,3,50},
            {121.40357,25.127599,0,0,3,80},
            {121.393197,25.138911,0,0,1,50},
            {121.425336,25.159333,0,0,5,50},
            {121.4428668,25.15064222,0,0,7,50},
            {121.445799,25.147147,0,0,2,50},
            {121.503471,25.117651,0,0,-1,50},
            {121.488824,25.059156,0,0,3,50},
            {121.489712,25.030508,0,0,7,50},
            {121.496688,25.033052,0,0,5,50},
            {121.493290,25.030309,0,0,1,50},
            {121.497173,25.035295,0,0,-1,50},
            {121.496167,25.029802,0,0,1,50},
            {121.46113,25.093583,0,0,7,50}
    };
}