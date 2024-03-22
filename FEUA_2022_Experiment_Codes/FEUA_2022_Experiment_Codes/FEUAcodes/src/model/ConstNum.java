package model;

public class ConstNum {
	public static int nUser = 4;
	public static int nServer = 2;
	public static int dResource = 4;
	
	public static double nTimes = 1;
	public static int nPhysicalMachines = 5;
	
	public static int B = 50000; //500
	
	public static final double penalty = 2;
	public static final double idleEnergyConsumption = 100;
	public static final double peakEnergyConsumption = 300;
	public static final double EARTH_RADIUS = 6378137;
	public static double pathLossAlphaNOMA = -5;
	public static double frequencyDependceNOMA = 1;
	public static double nosieNOMA = 0.00000000000001; // -100dBm = 10^-14 watts
	public static int nChannel = 5; //5
	public static int power = 1;
	
	public static double Rmin = 0;
	public static double Rmax = 20;
	public static double Pmin = 0.1;
	public static double Pmax = 0.3;
	
	public static int nSmallServers4Set23 = 10; //4, 6, 8, 10, 12, 14, 16, 18
	public static int nSmallUsers4Set13 = 20; // 5, 10, 15, 20, 25, 30, 35, 40 
	public static int nSmallCapacity4Set12 = 4; //1, 2, 3, 4, 5, 6, 7, 8
	
	public static int nLargeServers4Set23 = 200; //50, 100, 150, 200, 250, 300, 350, 400
	public static int nLargeUsers4Set13 = 256; // 16,32, 64, 128, 256, 512, 1024, 2048
	public static int nLargeCapacity4Set12 = 40; // 10, 20, 30, 40, 50, 60, 70, 80
	
	public static double channelGainFactor = -4;
	public static double distDiffChannelGainFactor = 100;
	public static double bandChannelGainFactor = -9;
	public static double baseChannelGainFactor = 1000;
}
