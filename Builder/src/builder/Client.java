package builder;

import org.deeplearning4j.nn.weights.WeightInit;

public class Client {
	public static void main(String args[]) {
		// this builder has duplicated the fields of NeuralNetConfiguration
		NeuralNetConfiguration config = new NeuralNetConfiguration.Builder()
				.layer(new org.deeplearning4j.nn.conf.layers.OutputLayer())
				.nIn(4)
				.nOut(3)
				.activationFunction("softmax")
				.iterations(10)
				.weightInit(WeightInit.XAVIER)
				.learningRate(1e-1)
				.seed(12345L)
				.build();
		
		// the following builder use a reference to NeuralNetConfiguration as a field
		// in order to read or modify directly the reference's attributes
		NeuralNetConfiguration defaultConfig =
				new NeuralNetConfiguration.DefaultBuilder().build();
		
		NeuralNetConfiguration advancedConfig =
				new NeuralNetConfiguration.AdvancedBuilder()
				.layer(new org.deeplearning4j.nn.conf.layers.OutputLayer())
				.nIn(4)
				.nOut(3)
				.activationFunction("softmax")
				.iterations(10)
				.weightInit(WeightInit.XAVIER)
				.learningRate(1e-1)
				.seed(12345L)
				.build();
	}
}
