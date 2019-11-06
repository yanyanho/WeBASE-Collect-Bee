package org.fisco.bcos.temp;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes4;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class BAC003 extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620028db380380620028db8339810180604052810190808051820192919050505080600590805190602001906200004e92919062000056565b505062000105565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200009957805160ff1916838001178555620000ca565b82800160010185558215620000ca579182015b82811115620000c9578251825591602001919060010190620000ac565b5b509050620000d99190620000dd565b5090565b6200010291905b80821115620000fe576000816000905550600101620000e4565b5090565b90565b6127c680620001156000396000f3006080604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630e89341c146100e0578063255e6e88146101865780632eb2c2d6146102835780633ad424171461032e5780634e1273f41461036f57806367db3b8f146104175780636d8c859a1461045c578063a22cb465146104bd578063a8adb0fd1461050c578063affed0e014610581578063ba5c6713146105ac578063c8d30ebc14610629578063e985e9c51461069a578063f242432a14610715578063fe20e3e6146107a4575b600080fd5b3480156100ec57600080fd5b5061010b60048036038101908080359060200190929190505050610885565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561014b578082015181840152602081019050610130565b50505050905090810190601f1680156101785780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561019257600080fd5b5061022f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939050505061093a565b60405180827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200191505060405180910390f35b34801561028f57600080fd5b5061032c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939050505061096e565b005b34801561033a57600080fd5b5061035960048036038101908080359060200190929190505050610f92565b6040518082815260200191505060405180910390f35b34801561037b57600080fd5b506103c0600480360381019080803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050610faf565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156104035780820151818401526020810190506103e8565b505050509050019250505060405180910390f35b34801561042357600080fd5b5061045a6004803603810190808035906020019082018035906020019190919293919293908035906020019092919050505061115e565b005b34801561046857600080fd5b506104a7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506112b0565b6040518082815260200191505060405180910390f35b3480156104c957600080fd5b5061050a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080351515906020019092919050505061130a565b005b34801561051857600080fd5b5061057f6004803603810190808035906020019092919080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939050505061140b565b005b34801561058d57600080fd5b506105966117d2565b6040518082815260200191505060405180910390f35b3480156105b857600080fd5b506106276004803603810190808035906020019092919080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506117d8565b005b34801561063557600080fd5b50610684600480360381019080803590602001909291908035906020019082018035906020019190919293919293908035906020019082018035906020019190919293919293905050506119c7565b6040518082815260200191505060405180910390f35b3480156106a657600080fd5b506106fb600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611bca565b604051808215151515815260200191505060405180910390f35b34801561072157600080fd5b506107a2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190803590602001908201803590602001919091929391929390505050611c5e565b005b3480156107b057600080fd5b50610831600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001909291908035906020019082018035906020019190919293919293905050506120e1565b60405180827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200191505060405180910390f35b6060600360008381526020019081526020016000208054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561092e5780601f106109035761010080835404028352916020019161092e565b820191906000526020600020905b81548152906001019060200180831161091157829003601f168201915b50505050509050919050565b600063255e6e887c010000000000000000000000000000000000000000000000000000000002905098975050505050505050565b60008060008073ffffffffffffffffffffffffffffffffffffffff168a73ffffffffffffffffffffffffffffffffffffffff1614151515610a3d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001807f64657374696e6174696f6e2061646472657373206d757374206265206e6f6e2d81526020017f7a65726f2e00000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8686905089899050141515610ae0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260298152602001807f5f69647320616e64205f76616c756573206172726179206c656e676874206d7581526020017f7374206d617463682e000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff168b73ffffffffffffffffffffffffffffffffffffffff161480610ba7575060011515600160008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b1515610c41576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f4e656564206f70657261746f7220617070726f76616c20666f7220337264207081526020017f61727479207472616e73666572732e000000000000000000000000000000000081525060400191505060405180910390fd5b600092505b88889050831015610ec2578888848181101515610c5f57fe5b9050602002013591508686848181101515610c7657fe5b905060200201359050610ce18160008085815260200190815260200160002060008e73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461211390919063ffffffff16565b60008084815260200190815260200160002060008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550610d9660008084815260200190815260200160002060008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548261212c90919063ffffffff16565b60008084815260200190815260200160002060008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508973ffffffffffffffffffffffffffffffffffffffff168b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f08c8c888181101515610e5d57fe5b905060200201358b8b898181101515610e7257fe5b905060200201358a8a6040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a4826001019250610c46565b610ee18a73ffffffffffffffffffffffffffffffffffffffff16612148565b15610f8557610f84338c8c8c8c808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050508b8b808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050508a8a8080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505061215b565b5b5050505050505050505050565b600060066000838152602001908152602001600020549050919050565b60608060008484905087879050141515611057576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a8152602001807f205f6f776e65727320616e64205f696473206172726179206c656e676874206d81526020017f757374206d617463682e0000000000000000000000000000000000000000000081525060400191505060405180910390fd5b868690506040519080825280602002602001820160405280156110895781602001602082028038833980820191505090505b509150600090505b868690508110156111515760008086868481811015156110ad57fe5b905060200201358152602001908152602001600020600088888481811015156110d257fe5b9050602002013573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054828281518110151561113857fe5b9060200190602002018181525050806001019050611091565b8192505050949350505050565b803373ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611235576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f6d73672e73656e646572206973206e6f7420746865206f776e6572000000000081525060200191505060405180910390fd5b83836003600085815260200190815260200160002091906112579291906126f5565b50817f6bb7ff708619ba0610cba295a58592e0451dee2622938c8755667688daf3529b8585604051808060200182810382528484828181526020019250808284378201915050935050505060405180910390a250505050565b600080600083815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b80600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c3183604051808215151515815260200191505060405180910390a35050565b6000806000893373ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156114e7576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f6d73672e73656e646572206973206e6f7420746865206f776e6572000000000081525060200191505060405180910390fd5b878790508a8a905014151561158a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602c8152602001807f5f746f20616e64205f7175616e746974696573206172726179206c656e67687481526020017f206d757374206d617463682e000000000000000000000000000000000000000081525060400191505060405180910390fd5b600093505b898990508410156117c55789898581811015156115a857fe5b9050602002013573ffffffffffffffffffffffffffffffffffffffff16925087878581811015156115d557fe5b9050602002013591506116406000808d815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548361212c90919063ffffffff16565b6000808d815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506116b982600660008e81526020019081526020016000205461212c90919063ffffffff16565b600660008d8152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f08e868b8b6040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a46117968373ffffffffffffffffffffffffffffffffffffffff16612148565b156117ba576117b93333858e866020604051908101604052806000815250612469565b5b83600101935061158f565b5050505050505050505050565b60045481565b600033905061180383600660008781526020019081526020016000205461211390919063ffffffff16565b600660008681526020019081526020016000208190555061187c8360008087815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461211390919063ffffffff16565b60008086815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f08787876040518084815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561198557808201518184015260208101905061196a565b50505050905090810190601f1680156119b25780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a450505050565b60006004600081546001019190508190559050336002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508560008083815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508560066000838152602001908152602001600020819055503373ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f0848a88886040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a46000858590501115611bc1578484600360008481526020019081526020016000209190611b6d9291906126f5565b50807f6bb7ff708619ba0610cba295a58592e0451dee2622938c8755667688daf3529b8686604051808060200182810382528484828181526020019250808284378201915050935050505060405180910390a25b95945050505050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b600073ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1614151515611d03576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f5f746f206d757374206265206e6f6e2d7a65726f2e000000000000000000000081525060200191505060405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff161480611dca575060011515600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b1515611e64576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f4e656564206f70657261746f7220617070726f76616c20666f7220337264207081526020017f61727479207472616e73666572732e000000000000000000000000000000000081525060400191505060405180910390fd5b611ec68360008087815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461211390919063ffffffff16565b60008086815260200190815260200160002060008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611f7b60008086815260200190815260200160002060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548461212c90919063ffffffff16565b60008086815260200190815260200160002060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f0878787876040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a46120938573ffffffffffffffffffffffffffffffffffffffff16612148565b156120d9576120d8338787878787878080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050612469565b5b505050505050565b600063fe20e3e67c01000000000000000000000000000000000000000000000000000000000290509695505050505050565b600082821115151561212157fe5b818303905092915050565b6000818301905082811015151561213f57fe5b80905092915050565b600080823b905060008111915050919050565b63255e6e887c0100000000000000000000000000000000000000000000000000000000027bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168473ffffffffffffffffffffffffffffffffffffffff1663255e6e8888888787876040518663ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001848103845287818151815260200191508051906020019060200280838360005b8381101561229957808201518184015260208101905061227e565b50505050905001848103835286818151815260200191508051906020019060200280838360005b838110156122db5780820151818401526020810190506122c0565b50505050905001848103825285818151815260200191508051906020019080838360005b8381101561231a5780820151818401526020810190506122ff565b50505050905090810190601f1680156123475780820380516001836020036101000a031916815260200191505b5098505050505050505050602060405180830381600087803b15801561236c57600080fd5b505af1158015612380573d6000803e3d6000fd5b505050506040513d602081101561239657600080fd5b81019080805190602001909291905050507bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141515612461576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f636f6e74726163742072657475726e656420616e20756e6b6e6f776e2076616c81526020017f75652066726f6d206f6e4241433030334261746368526563656976656400000081525060400191505060405180910390fd5b505050505050565b63fe20e3e67c0100000000000000000000000000000000000000000000000000000000027bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168473ffffffffffffffffffffffffffffffffffffffff1663fe20e3e688888787876040518663ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156125a857808201518184015260208101905061258d565b50505050905090810190601f1680156125d55780820380516001836020036101000a031916815260200191505b509650505050505050602060405180830381600087803b1580156125f857600080fd5b505af115801561260c573d6000803e3d6000fd5b505050506040513d602081101561262257600080fd5b81019080805190602001909291905050507bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415156126ed576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260388152602001807f636f6e74726163742072657475726e656420616e20756e6b6e6f776e2076616c81526020017f75652066726f6d206f6e4241433030335265636569766564000000000000000081525060400191505060405180910390fd5b505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061273657803560ff1916838001178555612764565b82800160010185558215612764579182015b82811115612763578235825591602001919060010190612748565b5b5090506127719190612775565b5090565b61279791905b8082111561279357600081600090555060010161277b565b5090565b905600a165627a7a723058206085e65554bbb7dc71542995f7da58f0ff341002a9f7cf2ffee2b9da2e2eaed10029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"uri\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_operator\",\"type\":\"address\"},{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_ids\",\"type\":\"uint256[]\"},{\"name\":\"_values\",\"type\":\"uint256[]\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"onBAC003BatchReceived\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes4\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_ids\",\"type\":\"uint256[]\"},{\"name\":\"_values\",\"type\":\"uint256[]\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"safeBatchTransferFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"totalAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owners\",\"type\":\"address[]\"},{\"name\":\"_ids\",\"type\":\"uint256[]\"}],\"name\":\"balanceOfBatch\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_uri\",\"type\":\"string\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"setURI\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"balance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_operator\",\"type\":\"address\"},{\"name\":\"_approved\",\"type\":\"bool\"}],\"name\":\"setApprovalForAll\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_to\",\"type\":\"address[]\"},{\"name\":\"_quantities\",\"type\":\"uint256[]\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"increase\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"nonce\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"destroy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_initialSupply\",\"type\":\"uint256\"},{\"name\":\"_uri\",\"type\":\"string\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"create\",\"outputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"},{\"name\":\"_operator\",\"type\":\"address\"}],\"name\":\"isApprovedForAll\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"safeTransferFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_operator\",\"type\":\"address\"},{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"onBAC003Received\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes4\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"description\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_operator\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_value\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"TransferSingle\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_operator\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_ids\",\"type\":\"uint256[]\"},{\"indexed\":false,\"name\":\"_values\",\"type\":\"uint256[]\"}],\"name\":\"TransferBatch\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_owner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_operator\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_approved\",\"type\":\"bool\"}],\"name\":\"ApprovalForAll\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_value\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"URI\",\"type\":\"event\"}]";

    public static final String FUNC_URI = "uri";

    public static final String FUNC_ONBAC003BATCHRECEIVED = "onBAC003BatchReceived";

    public static final String FUNC_SAFEBATCHTRANSFERFROM = "safeBatchTransferFrom";

    public static final String FUNC_TOTALAMOUNT = "totalAmount";

    public static final String FUNC_BALANCEOFBATCH = "balanceOfBatch";

    public static final String FUNC_SETURI = "setURI";

    public static final String FUNC_BALANCE = "balance";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_INCREASE = "increase";

    public static final String FUNC_NONCE = "nonce";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_CREATE = "create";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_SAFETRANSFERFROM = "safeTransferFrom";

    public static final String FUNC_ONBAC003RECEIVED = "onBAC003Received";

    public static final Event TRANSFERSINGLE_EVENT = new Event("TransferSingle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event TRANSFERBATCH_EVENT = new Event("TransferBatch", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event URI_EVENT = new Event("URI", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected BAC003(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BAC003(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BAC003(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BAC003(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> uri(BigInteger _id) {
        final Function function = new Function(FUNC_URI, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> onBAC003BatchReceived(String _operator, String _from, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data) {
        final Function function = new Function(FUNC_ONBAC003BATCHRECEIVED, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_operator), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> safeBatchTransferFrom(String _from, String _to, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void safeBatchTransferFrom(String _from, String _to, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String safeBatchTransferFromSeq(String _from, String _to, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> totalAmount(BigInteger _id) {
        final Function function = new Function(FUNC_TOTALAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> balanceOfBatch(List<String> _owners, List<BigInteger> _ids) {
        final Function function = new Function(FUNC_BALANCEOFBATCH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_owners, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setURI(String _uri, BigInteger _id) {
        final Function function = new Function(
                FUNC_SETURI, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_uri), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setURI(String _uri, BigInteger _id, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETURI, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_uri), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setURISeq(String _uri, BigInteger _id) {
        final Function function = new Function(
                FUNC_SETURI, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_uri), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> balance(String _owner, BigInteger _id) {
        final Function function = new Function(FUNC_BALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_owner), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setApprovalForAll(String _operator, Boolean _approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_operator), 
                new org.fisco.bcos.web3j.abi.datatypes.Bool(_approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setApprovalForAll(String _operator, Boolean _approved, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_operator), 
                new org.fisco.bcos.web3j.abi.datatypes.Bool(_approved)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setApprovalForAllSeq(String _operator, Boolean _approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_operator), 
                new org.fisco.bcos.web3j.abi.datatypes.Bool(_approved)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> increase(BigInteger _id, List<String> _to, List<BigInteger> _quantities, byte[] _data) {
        final Function function = new Function(
                FUNC_INCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_to, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_quantities, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void increase(BigInteger _id, List<String> _to, List<BigInteger> _quantities, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_to, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_quantities, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String increaseSeq(BigInteger _id, List<String> _to, List<BigInteger> _quantities, byte[] _data) {
        final Function function = new Function(
                FUNC_INCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_to, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_quantities, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> nonce() {
        final Function function = new Function(FUNC_NONCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> destroy(BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_DESTROY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void destroy(BigInteger _id, BigInteger _value, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DESTROY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String destroySeq(BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_DESTROY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> create(BigInteger _initialSupply, String _uri, byte[] _data) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_initialSupply), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_uri), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(BigInteger _initialSupply, String _uri, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_initialSupply), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_uri), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createSeq(BigInteger _initialSupply, String _uri, byte[] _data) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_initialSupply), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_uri), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Boolean> isApprovedForAll(String _owner, String _operator) {
        final Function function = new Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_owner), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> safeTransferFrom(String _from, String _to, BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void safeTransferFrom(String _from, String _to, BigInteger _id, BigInteger _value, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String safeTransferFromSeq(String _from, String _to, BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<byte[]> onBAC003Received(String _operator, String _from, BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(FUNC_ONBAC003RECEIVED, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_operator), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_data)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public List<TransferSingleEventResponse> getTransferSingleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERSINGLE_EVENT, transactionReceipt);
        ArrayList<TransferSingleEventResponse> responses = new ArrayList<TransferSingleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferSingleEventResponse typedResponse = new TransferSingleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse._id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._data = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferSingleEventResponse> transferSingleEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferSingleEventResponse>() {
            @Override
            public TransferSingleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERSINGLE_EVENT, log);
                TransferSingleEventResponse typedResponse = new TransferSingleEventResponse();
                typedResponse.log = log;
                typedResponse._operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse._id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._data = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferSingleEventResponse> transferSingleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERSINGLE_EVENT));
        return transferSingleEventFlowable(filter);
    }

    public List<TransferBatchEventResponse> getTransferBatchEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERBATCH_EVENT, transactionReceipt);
        ArrayList<TransferBatchEventResponse> responses = new ArrayList<TransferBatchEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferBatchEventResponse typedResponse = new TransferBatchEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse._ids = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._values = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferBatchEventResponse> transferBatchEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferBatchEventResponse>() {
            @Override
            public TransferBatchEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERBATCH_EVENT, log);
                TransferBatchEventResponse typedResponse = new TransferBatchEventResponse();
                typedResponse.log = log;
                typedResponse._operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse._ids = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._values = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferBatchEventResponse> transferBatchEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERBATCH_EVENT));
        return transferBatchEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<URIEventResponse> getURIEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(URI_EVENT, transactionReceipt);
        ArrayList<URIEventResponse> responses = new ArrayList<URIEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            URIEventResponse typedResponse = new URIEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._value = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<URIEventResponse> uRIEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, URIEventResponse>() {
            @Override
            public URIEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(URI_EVENT, log);
                URIEventResponse typedResponse = new URIEventResponse();
                typedResponse.log = log;
                typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._value = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<URIEventResponse> uRIEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(URI_EVENT));
        return uRIEventFlowable(filter);
    }

    @Deprecated
    public static BAC003 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BAC003(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BAC003 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BAC003(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BAC003 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BAC003(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BAC003 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BAC003(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BAC003> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(description)));
        return deployRemoteCall(BAC003.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<BAC003> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(description)));
        return deployRemoteCall(BAC003.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BAC003> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(description)));
        return deployRemoteCall(BAC003.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BAC003> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(description)));
        return deployRemoteCall(BAC003.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class TransferSingleEventResponse {
        public Log log;

        public String _operator;

        public String _from;

        public String _to;

        public BigInteger _id;

        public BigInteger _value;

        public byte[] _data;
    }

    public static class TransferBatchEventResponse {
        public Log log;

        public String _operator;

        public String _from;

        public String _to;

        public List<BigInteger> _ids;

        public List<BigInteger> _values;
    }

    public static class ApprovalForAllEventResponse {
        public Log log;

        public String _owner;

        public String _operator;

        public Boolean _approved;
    }

    public static class URIEventResponse {
        public Log log;

        public BigInteger _id;

        public String _value;
    }
}
