package builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.distribution.Distribution;
import org.deeplearning4j.nn.conf.distribution.NormalDistribution;
import org.deeplearning4j.nn.conf.layers.Layer;
import org.deeplearning4j.nn.conf.layers.RBM;
import org.deeplearning4j.nn.conf.rng.DefaultRandom;
import org.deeplearning4j.nn.conf.stepfunctions.GradientStepFunction;
import org.deeplearning4j.nn.conf.stepfunctions.DefaultStepFunction;
import org.deeplearning4j.nn.conf.stepfunctions.StepFunction;
import org.deeplearning4j.nn.weights.WeightInit;

public class NeuralNetConfiguration implements Serializable,Cloneable {

	private double sparsity = 0;
	private boolean useAdaGrad = true;
	private double lr = 1e-1;
	private double corruptionLevel = 0.3;
	private int numIterations = 1000;
	/* momentum for learning */
	private double momentum = 0.5;
	/* L2 Regularization constant */
	private double l2 = 0;
	private boolean useRegularization = false;

	private String customLossFunction;
	//momentum after n iterations
	private Map<Integer,Double> momentumAfter = new HashMap<>();
	//reset adagrad historical gradient after n iterations
	private int resetAdaGradIterations = -1;
	//number of line search iterations
	private int numLineSearchIterations = 100;

	private double dropOut = 0;
	//use only when binary hidden neuralNets are active
	private boolean applySparsity = false;
	//weight init scheme, this can either be a distribution or a applyTransformToDestination scheme
	private WeightInit weightInit = WeightInit.VI;
	private OptimizationAlgorithm optimizationAlgo = OptimizationAlgorithm.CONJUGATE_GRADIENT;
	//whether to constrain the gradient to unit norm or not
	private boolean constrainGradientToUnitNorm = false;
	/* RNG for sampling. */
	@Deprecated
	private transient DefaultRandom rng;
	private long seed;
	//weight initialization
	private Distribution dist;
	private StepFunction stepFunction = new GradientStepFunction();
	private Layer layer;

	//gradient keys used for ensuring order when getting and setting the gradient
	private List<String> variables = new ArrayList<>();
	//feed forward nets
	private int nIn,nOut;

	private String activationFunction;

	private boolean useDropConnect = false;

	//RBMs
	private RBM.VisibleUnit visibleUnit = RBM.VisibleUnit.BINARY;
	private RBM.HiddenUnit hiddenUnit = RBM.HiddenUnit.BINARY;
	private int k = 1;

	private int[] weightShape;

	//convolutional nets: this is the feature map shape
	private int[] filterSize = {2,2};
	//aka pool size for subsampling
	private int[] stride = {2,2};
	//kernel size for a convolutional net
	@Deprecated
	private int kernel = 5;
	//batch size: primarily used for conv nets. Will be reinforced if set.
	private int batchSize = 10;
	//minimize or maximize objective
	private boolean minimize = false;
	//l1 regularization
	private double l1 = 0.0;
	//feature map
	private int[] featureMapSize = {9,9};

	private double rmsDecay = 0.0;

	private NeuralNetConfiguration() {}
	private NeuralNetConfiguration(double sparsity,
			boolean useAdaGrad,
			double lr,
			int k,
			double corruptionLevel,
			int numIterations,
			double momentum,
			double l2,
			boolean useRegularization,
			Map<Integer, Double> momentumAfter,
			int resetAdaGradIterations,
			double dropOut,
			boolean applySparsity,
			WeightInit weightInit,
			OptimizationAlgorithm optimizationAlgo,
			boolean constrainGradientToUnitNorm,
			DefaultRandom rng,
			long seed,
			Distribution dist,
			int nIn,
			int nOut,
			String activationFunction,
			RBM.VisibleUnit visibleUnit,
			RBM.HiddenUnit hiddenUnit,
			int[] weightShape,
			int[] filterSize,
			int[] stride,
			int[] featureMapSize,
			int kernel,
			int batchSize,
			int numLineSearchIterations,
			boolean minimize,
			Layer layer,double l1,String customLossFunction,
			double rmsDecay, StepFunction stepFunction) {
		this.minimize = minimize;
		this.customLossFunction = customLossFunction;
		this.numLineSearchIterations = numLineSearchIterations;
		this.featureMapSize = featureMapSize;
		this.l1 = l1;
		this.batchSize = batchSize;
		if (layer == null) {
			throw new IllegalStateException("No layer defined.");
		} else {
			this.layer = layer;
		}
		this.sparsity = sparsity;
		this.useAdaGrad = useAdaGrad;
		this.lr = lr;
		this.kernel = kernel;
		this.k = k;
		this.corruptionLevel = corruptionLevel;
		this.numIterations = numIterations;
		this.momentum = momentum;
		this.l2 = l2;
		this.useRegularization = useRegularization;
		this.momentumAfter = momentumAfter;
		this.resetAdaGradIterations = resetAdaGradIterations;
		this.dropOut = dropOut;
		this.applySparsity = applySparsity;
		this.weightInit = weightInit;
		this.optimizationAlgo = optimizationAlgo;
		this.constrainGradientToUnitNorm = constrainGradientToUnitNorm;
		this.rng = null;
		this.seed = seed;
		this.dist = dist;
		this.nIn = nIn;
		this.nOut = nOut;
		this.activationFunction = activationFunction;
		this.visibleUnit = visibleUnit;
		this.hiddenUnit = hiddenUnit;
		if(weightShape != null)
			this.weightShape = weightShape;
		else
			this.weightShape = new int[]{nIn,nOut};
		this.filterSize = filterSize;
		this.stride = stride;
		this.rmsDecay = rmsDecay;
		this.stepFunction = stepFunction;
	}
	
	private interface AbstractBuilder {
		public NeuralNetConfiguration build();
	}
	
	public static class DefaultBuilder implements AbstractBuilder {

		private NeuralNetConfiguration conf = new NeuralNetConfiguration();

		@Override
		public NeuralNetConfiguration build() {
			NeuralNetConfiguration ret = new NeuralNetConfiguration(conf.sparsity, conf.useAdaGrad, conf.lr, conf.k,
				conf.corruptionLevel, conf.numIterations, conf.momentum, conf.l2, conf.useRegularization, conf.momentumAfter,
				conf.resetAdaGradIterations, conf.dropOut, conf.applySparsity, conf.weightInit, conf.optimizationAlgo,
				conf.constrainGradientToUnitNorm,  conf.rng, conf.seed,
				conf.dist,  conf.nIn, conf.nOut, conf.activationFunction, conf.visibleUnit, conf.hiddenUnit, conf.weightShape,
				conf.filterSize, conf.stride, conf.featureMapSize, conf.kernel, conf.batchSize,
				conf.numLineSearchIterations, conf.minimize, conf.layer, conf.l1, conf.customLossFunction, conf.rmsDecay, conf.stepFunction);
			return ret;
		}
	}
	
	public static class AdvancedBuilder implements AbstractBuilder {

		private NeuralNetConfiguration conf = new NeuralNetConfiguration();

		/**
		 * Use drop connect: multiply the coefficients
		 * by a binomial sampling wrt the dropout probability
		 * @param useDropConnect whether to use drop connect or not
		 * @return the
		 */
		public AdvancedBuilder useDropConnect(boolean useDropConnect) {
			conf.useDropConnect = useDropConnect;
			return this;
		}

		public AdvancedBuilder l1(double l1) {
			conf.l1 = l1;
			return this;
		}


		public AdvancedBuilder rmsDecay(double rmsDecay) {
			conf.rmsDecay = rmsDecay;
			return this;
		}

		public AdvancedBuilder customLossFunction(String customLossFunction) {
			conf.customLossFunction = customLossFunction;
			return this;
		}

		public AdvancedBuilder minimize(boolean minimize) {
			conf.minimize = minimize;
			return this;
		}

		public AdvancedBuilder numLineSearchIterations(int numLineSearchIterations) {
			conf.numLineSearchIterations = numLineSearchIterations;
			return this;
		}

		public AdvancedBuilder batchSize(int batchSize) {
			conf.batchSize = batchSize;
			return this;
		}

		public AdvancedBuilder kernel(int kernel) {
			conf.kernel = kernel;
			return this;
		}

		public AdvancedBuilder layer(Layer layer) {
			conf.layer = layer;
			return this;
		}

		public AdvancedBuilder stepFunction(StepFunction stepFunction) {
			conf.stepFunction = stepFunction;
			return this;
		}

		public AdvancedBuilder featureMapSize(int...featureMapSize) {
			conf.featureMapSize = featureMapSize;
			return this;
		}


		public AdvancedBuilder stride(int[] stride) {
			conf.stride = stride;
			return this;
		}

		public AdvancedBuilder filterSize(int...filterSize) {
			if(filterSize == null)
				return this;
			if(filterSize.length != 4)
				throw new IllegalArgumentException("Invalid filter size must be length 4");
			conf.filterSize = filterSize;
			return this;
		}

		public AdvancedBuilder weightShape(int[] weightShape) {
			conf.weightShape = weightShape;
			return this;
		}

		public AdvancedBuilder iterations(int numIterations) {
			conf.numIterations = numIterations;
			return this;
		}

		public AdvancedBuilder dist(Distribution dist) {
			conf.dist = dist;
			return this;
		}

		public AdvancedBuilder sparsity(double sparsity) {
			conf.sparsity = sparsity;
			return this;
		}

		public AdvancedBuilder useAdaGrad(boolean useAdaGrad) {
			conf.useAdaGrad = useAdaGrad;
			return this;
		}

		public AdvancedBuilder learningRate(double lr) {
			conf.lr = lr;
			return this;
		}

		public AdvancedBuilder momentum(double momentum) {
			conf.momentum = momentum;
			return this;
		}

		public AdvancedBuilder k(int k) {
			conf.k = k;
			return this;
		}

		public AdvancedBuilder corruptionLevel(double corruptionLevel) {
			conf.corruptionLevel = corruptionLevel;
			return this;
		}

		public AdvancedBuilder momentumAfter(Map<Integer, Double> momentumAfter) {
			conf.momentumAfter = momentumAfter;
			return this;
		}

		@Deprecated
		public AdvancedBuilder adagradResetIterations(int resetAdaGradIterations) {
			conf.resetAdaGradIterations = resetAdaGradIterations;
			return this;
		}

		public AdvancedBuilder dropOut(double dropOut) {
			conf.dropOut = dropOut;
			return this;
		}

		public AdvancedBuilder applySparsity(boolean applySparsity) {
			conf.applySparsity = applySparsity;
			return this;
		}

		public AdvancedBuilder weightInit(WeightInit weightInit) {
			conf.weightInit = weightInit;
			return this;
		}

		@Deprecated
		public AdvancedBuilder rng(DefaultRandom rng) {
			conf.rng = rng;
			return this;
		}

		public AdvancedBuilder seed(int seed) {
			conf.seed = (long) seed;
			return this;
		}

		public AdvancedBuilder seed(long seed) {
			conf.seed = seed;
			return this;
		}

		/**
		 * Return a configuration based on this builder
		 *
		 * @return
		 */
		public NeuralNetConfiguration build() {
			return conf;
		}

		public AdvancedBuilder l2(double l2) {
			conf.l2 = l2;
			return this;
		}

		public AdvancedBuilder regularization(boolean useRegularization) {
			conf.useRegularization = useRegularization;
			return this;
		}

		public AdvancedBuilder resetAdaGradIterations(int resetAdaGradIterations) {
			conf.resetAdaGradIterations = resetAdaGradIterations;
			return this;
		}

		public AdvancedBuilder optimizationAlgo(OptimizationAlgorithm optimizationAlgo) {
			conf.optimizationAlgo = optimizationAlgo;
			return this;
		}

		public AdvancedBuilder constrainGradientToUnitNorm(boolean constrainGradientToUnitNorm) {
			conf.constrainGradientToUnitNorm = constrainGradientToUnitNorm;
			return this;
		}

		public AdvancedBuilder nIn(int nIn) {
			conf.nIn = nIn;
			return this;
		}

		public AdvancedBuilder nOut(int nOut) {
			conf.nOut = nOut;
			return this;
		}

		public AdvancedBuilder activationFunction(String activationFunction) {
			conf.activationFunction = activationFunction;
			return this;
		}

		public AdvancedBuilder visibleUnit(RBM.VisibleUnit visibleUnit) {
			conf.visibleUnit = visibleUnit;
			return this;
		}

		public AdvancedBuilder hiddenUnit(RBM.HiddenUnit hiddenUnit) {
			conf.hiddenUnit = hiddenUnit;
			return this;
		}
	}
	
	public static class Builder implements AbstractBuilder {
		private int k = 1;
		private String customLossFunction;
		private double rmsDecay;
		@Deprecated
		private int kernel = 5;
		private double corruptionLevel = 3e-1f;
		private double sparsity = 0f;
		private boolean useAdaGrad = true;
		private double lr = 1e-1f;
		private double momentum = 0.5f;
		private double l2 = 0f;
		private boolean useRegularization = false;
		private Map<Integer, Double> momentumAfter;
		private int resetAdaGradIterations = -1;
		private double dropOut = 0;
		private boolean applySparsity = false;
		private WeightInit weightInit = WeightInit.VI;
		private OptimizationAlgorithm optimizationAlgo = OptimizationAlgorithm.CONJUGATE_GRADIENT;
		private boolean constrainGradientToUnitNorm = false;
		@Deprecated
		private DefaultRandom rng = null;
		private long seed = System.currentTimeMillis();
		private Distribution dist  = new NormalDistribution(1e-3,1);
		private int nIn;
		private int nOut;
		private String activationFunction = "sigmoid";
		private RBM.VisibleUnit visibleUnit = RBM.VisibleUnit.BINARY;
		private RBM.HiddenUnit hiddenUnit = RBM.HiddenUnit.BINARY;
		private int numIterations = 1000;
		private int[] weightShape;
		private int[] filterSize = {2,2,2,2};
		private int[] featureMapSize = {2,2};
		//subsampling layers
		private int[] stride = {2,2};
		private StepFunction stepFunction = new DefaultStepFunction();
		private Layer layer;
		private int batchSize = 100;
		private int numLineSearchIterations = 100;
		private boolean minimize = false;
		private double l1 = 0.0;
		private boolean useDropConnect = false;
		
		/**
		 * Use drop connect: multiply the coefficients
		 * by a binomial sampling wrt the dropout probability
		 * @param useDropConnect whether to use drop connect or not
		 * @return the
		 */
		public Builder useDropConnect(boolean useDropConnect) {
			this.useDropConnect = useDropConnect;
			return this;
		}

		public Builder l1(double l1) {
			this.l1 = l1;
			return this;
		}


		public Builder rmsDecay(double rmsDecay) {
			this.rmsDecay = rmsDecay;
			return this;
		}

		public Builder customLossFunction(String customLossFunction) {
			this.customLossFunction = customLossFunction;
			return this;
		}

		public Builder minimize(boolean minimize) {
			this.minimize = minimize;
			return this;
		}

		public Builder numLineSearchIterations(int numLineSearchIterations) {
			this.numLineSearchIterations = numLineSearchIterations;
			return this;
		}

		public Builder batchSize(int batchSize) {
			this.batchSize = batchSize;
			return this;
		}

		public Builder kernel(int kernel) {
			this.kernel = kernel;
			return this;
		}

		public Builder layer(Layer layer) {
			this.layer = layer;
			return this;
		}

		public Builder stepFunction(StepFunction stepFunction) {
			this.stepFunction = stepFunction;
			return this;
		}

		public Builder featureMapSize(int...featureMapSize) {
			this.featureMapSize = featureMapSize;
			return this;
		}


		public Builder stride(int[] stride) {
			this.stride = stride;
			return this;
		}

		public Builder filterSize(int...filterSize) {
			if(filterSize == null)
				return this;
			if(filterSize.length != 4)
				throw new IllegalArgumentException("Invalid filter size must be length 4");
			this.filterSize = filterSize;
			return this;
		}

		public Builder weightShape(int[] weightShape) {
			this.weightShape = weightShape;
			return this;
		}

		public Builder iterations(int numIterations) {
			this.numIterations = numIterations;
			return this;
		}

		public Builder dist(Distribution dist) {
			this.dist = dist;
			return this;
		}

		public Builder sparsity(double sparsity) {
			this.sparsity = sparsity;
			return this;
		}

		public Builder useAdaGrad(boolean useAdaGrad) {
			this.useAdaGrad = useAdaGrad;
			return this;
		}

		public Builder learningRate(double lr) {
			this.lr = lr;
			return this;
		}

		public Builder momentum(double momentum) {
			this.momentum = momentum;
			return this;
		}

		public Builder k(int k) {
			this.k = k;
			return this;
		}

		public Builder corruptionLevel(double corruptionLevel) {
			this.corruptionLevel = corruptionLevel;
			return this;
		}

		public Builder momentumAfter(Map<Integer, Double> momentumAfter) {
			this.momentumAfter = momentumAfter;
			return this;
		}

		@Deprecated
		public Builder adagradResetIterations(int resetAdaGradIterations) {
			this.resetAdaGradIterations = resetAdaGradIterations;
			return this;
		}

		public Builder dropOut(double dropOut) {
			this.dropOut = dropOut;
			return this;
		}

		public Builder applySparsity(boolean applySparsity) {
			this.applySparsity = applySparsity;
			return this;
		}

		public Builder weightInit(WeightInit weightInit) {
			this.weightInit = weightInit;
			return this;
		}

		@Deprecated
		public Builder rng(DefaultRandom rng) {
			this.rng = rng;
			return this;
		}

		public Builder seed(int seed) {
			this.seed = (long) seed;
			return this;
		}

		public Builder seed(long seed) {
			this.seed = seed;
			return this;
		}

		public Builder l2(double l2) {
			this.l2 = l2;
			return this;
		}

		public Builder regularization(boolean useRegularization) {
			this.useRegularization = useRegularization;
			return this;
		}

		public Builder resetAdaGradIterations(int resetAdaGradIterations) {
			this.resetAdaGradIterations = resetAdaGradIterations;
			return this;
		}

		public Builder optimizationAlgo(OptimizationAlgorithm optimizationAlgo) {
			this.optimizationAlgo = optimizationAlgo;
			return this;
		}

		public Builder constrainGradientToUnitNorm(boolean constrainGradientToUnitNorm) {
			this.constrainGradientToUnitNorm = constrainGradientToUnitNorm;
			return this;
		}

		public Builder nIn(int nIn) {
			this.nIn = nIn;
			return this;
		}

		public Builder nOut(int nOut) {
			this.nOut = nOut;
			return this;
		}

		public Builder activationFunction(String activationFunction) {
			this.activationFunction = activationFunction;
			return this;
		}

		public Builder visibleUnit(RBM.VisibleUnit visibleUnit) {
			this.visibleUnit = visibleUnit;
			return this;
		}

		public Builder hiddenUnit(RBM.HiddenUnit hiddenUnit) {
			this.hiddenUnit = hiddenUnit;
			return this;
		}
		
		/**
		 * Return a configuration based on this builder
		 *
		 * @return
		 */
		public NeuralNetConfiguration build() {
			NeuralNetConfiguration ret = new NeuralNetConfiguration(sparsity, useAdaGrad, lr, k,
					corruptionLevel, numIterations, momentum, l2, useRegularization, momentumAfter,
					resetAdaGradIterations, dropOut, applySparsity, weightInit, optimizationAlgo,
					constrainGradientToUnitNorm, rng, seed,
					dist, nIn, nOut, activationFunction, visibleUnit, hiddenUnit, weightShape, filterSize, stride, featureMapSize, kernel,
					batchSize, numLineSearchIterations, minimize, layer, l1, customLossFunction, rmsDecay, stepFunction);
			return ret;
		}
	}
}
