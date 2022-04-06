

package com.example.piedpiperd.customview;

import java.util.List;
import com.example.piedpiperd.tflite.Classifier.Recognition;

public interface ResultsView {
  public void setResults(final List<Recognition> results);
}
