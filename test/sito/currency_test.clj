(ns sito.currency-test
  (:require [clojure.test :refer :all]
            [sito.currency :refer :all]))

(deftest test-currency
  (testing "amount input conversion 1"
    (let [input "12,45"]
      (is (= 12.45 (convert-amount-str input)))))

  (testing "amount input conversion 2"
    (let [input "12hg"]
      (is (= nil (convert-amount-str input))))))
