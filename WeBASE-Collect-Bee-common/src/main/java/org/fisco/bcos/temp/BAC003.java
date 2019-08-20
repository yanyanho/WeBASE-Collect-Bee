package org.fisco.bcos.temp;

import io.reactivex.Flowable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.*;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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
    public static final String BINARY = "608060405234801561001057600080fd5b506124de806100206000396000f3006080604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063255e6e88146100e05780632eb2c2d6146101dd5780633ad42417146102885780634e1273f4146102c957806367db3b8f146103715780636d8c859a146103b6578063a22cb46514610417578063a8adb0fd14610466578063affed0e0146104db578063ba5c671314610506578063c8d30ebc14610583578063cd53d08e146105f4578063e985e9c514610661578063f242432a146106dc578063fe20e3e61461076b575b600080fd5b3480156100ec57600080fd5b50610189600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939050505061084c565b60405180827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200191505060405180910390f35b3480156101e957600080fd5b50610286600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050610880565b005b34801561029457600080fd5b506102b360048036038101908080359060200190929190505050610e94565b6040518082815260200191505060405180910390f35b3480156102d557600080fd5b5061031a600480360381019080803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050610eb1565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561035d578082015181840152602081019050610342565b505050509050019250505060405180910390f35b34801561037d57600080fd5b506103b460048036038101908080359060200190820180359060200191909192939192939080359060200190929190505050610fd1565b005b3480156103c257600080fd5b50610401600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611100565b6040518082815260200191505060405180910390f35b34801561042357600080fd5b50610464600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080351515906020019092919050505061115a565b005b34801561047257600080fd5b506104d96004803603810190808035906020019092919080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939050505061125b565b005b3480156104e757600080fd5b506104f061157f565b6040518082815260200191505060405180910390f35b34801561051257600080fd5b506105816004803603810190808035906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611585565b005b34801561058f57600080fd5b506105de60048036038101908080359060200190929190803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050611774565b6040518082815260200191505060405180910390f35b34801561060057600080fd5b5061061f60048036038101908080359060200190929190505050611954565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561066d57600080fd5b506106c2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611987565b604051808215151515815260200191505060405180910390f35b3480156106e857600080fd5b50610769600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190803590602001908201803590602001919091929391929390505050611a1b565b005b34801561077757600080fd5b506107f8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190803590602001908201803590602001919091929391929390505050611e9e565b60405180827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200191505060405180910390f35b600063255e6e887c010000000000000000000000000000000000000000000000000000000002905098975050505050505050565b60008060008073ffffffffffffffffffffffffffffffffffffffff168a73ffffffffffffffffffffffffffffffffffffffff161415151561094f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001807f64657374696e6174696f6e2061646472657373206d757374206265206e6f6e2d81526020017f7a65726f2e00000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b86869050898990501415156109f2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260298152602001807f5f69647320616e64205f76616c756573206172726179206c656e676874206d7581526020017f7374206d617463682e000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff168b73ffffffffffffffffffffffffffffffffffffffff161480610ab9575060011515600160008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b1515610b53576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f4e656564206f70657261746f7220617070726f76616c20666f7220337264207081526020017f61727479207472616e73666572732e000000000000000000000000000000000081525060400191505060405180910390fd5b600092505b88889050831015610d06578888848181101515610b7157fe5b9050602002013591508686848181101515610b8857fe5b905060200201359050610bf38160008085815260200190815260200160002060008e73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054611ed090919063ffffffff16565b60008084815260200190815260200160002060008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550610ca860008084815260200190815260200160002060008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205482611ee990919063ffffffff16565b60008084815260200190815260200160002060008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550826001019250610b58565b8973ffffffffffffffffffffffffffffffffffffffff168b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f4a39dc06d4c0dbc64b70af90fd698a233a518aa5d07e595d983b8c0526c8f7fb8c8c8c8c6040518080602001806020018381038352878782818152602001925060200280828437820191505083810382528585828181526020019250602002808284378201915050965050505050505060405180910390a4610de38a73ffffffffffffffffffffffffffffffffffffffff16611f05565b15610e8757610e86338c8c8c8c808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050508b8b808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050508a8a8080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050611f18565b5b5050505050505050505050565b600060046000838152602001908152602001600020549050919050565b60608060008484905087879050141515610eca57600080fd5b86869050604051908082528060200260200182016040528015610efc5781602001602082028038833980820191505090505b509150600090505b86869050811015610fc4576000808686848181101515610f2057fe5b90506020020135815260200190815260200160002060008888848181101515610f4557fe5b9050602002013573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548282815181101515610fab57fe5b9060200190602002018181525050806001019050610f04565b8192505050949350505050565b803373ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156110a8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f6d73672e73656e646572206973206e6f7420746865206f776e6572000000000081525060200191505060405180910390fd5b817f6bb7ff708619ba0610cba295a58592e0451dee2622938c8755667688daf3529b8585604051808060200182810382528484828181526020019250808284378201915050935050505060405180910390a250505050565b600080600083815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b80600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c3183604051808215151515815260200191505060405180910390a35050565b6000806000893373ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611337576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f6d73672e73656e646572206973206e6f7420746865206f776e6572000000000081525060200191505060405180910390fd5b600093505b8989905084101561157257898985818110151561135557fe5b9050602002013573ffffffffffffffffffffffffffffffffffffffff169250878785818110151561138257fe5b9050602002013591506113ed6000808d815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205483611ee990919063ffffffff16565b6000808d815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061146682600460008e815260200190815260200160002054611ee990919063ffffffff16565b600460008d8152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f08e868b8b6040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a46115438373ffffffffffffffffffffffffffffffffffffffff16611f05565b15611567576115663333858e866020604051908101604052806000815250612226565b5b83600101935061133c565b5050505050505050505050565b60035481565b60003390506115b0836004600087815260200190815260200160002054611ed090919063ffffffff16565b60046000868152602001908152602001600020819055506116298360008087815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054611ed090919063ffffffff16565b60008086815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f08787876040518084815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611732578082015181840152602081019050611717565b50505050905090810190601f16801561175f5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a450505050565b60006003600081546001019190508190559050336002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508560008083815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508560046000838152602001908152602001600020819055503373ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f0848a88886040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a4600085859050111561194b57807f6bb7ff708619ba0610cba295a58592e0451dee2622938c8755667688daf3529b8686604051808060200182810382528484828181526020019250808284378201915050935050505060405180910390a25b95945050505050565b60026020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b600073ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1614151515611ac0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f5f746f206d757374206265206e6f6e2d7a65726f2e000000000000000000000081525060200191505060405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff161480611b87575060011515600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b1515611c21576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f4e656564206f70657261746f7220617070726f76616c20666f7220337264207081526020017f61727479207472616e73666572732e000000000000000000000000000000000081525060400191505060405180910390fd5b611c838360008087815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054611ed090919063ffffffff16565b60008086815260200190815260200160002060008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611d3860008086815260200190815260200160002060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205484611ee990919063ffffffff16565b60008086815260200190815260200160002060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f562ef763935081bf16e4b400607546ff56def4639c3cbcc18631b55b3d0587f0878787876040518085815260200184815260200180602001828103825284848281815260200192508082843782019150509550505050505060405180910390a4611e508573ffffffffffffffffffffffffffffffffffffffff16611f05565b15611e9657611e95338787878787878080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050612226565b5b505050505050565b600063fe20e3e67c01000000000000000000000000000000000000000000000000000000000290509695505050505050565b6000828211151515611ede57fe5b818303905092915050565b60008183019050828110151515611efc57fe5b80905092915050565b600080823b905060008111915050919050565b63255e6e887c0100000000000000000000000000000000000000000000000000000000027bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168473ffffffffffffffffffffffffffffffffffffffff1663255e6e8888888787876040518663ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001848103845287818151815260200191508051906020019060200280838360005b8381101561205657808201518184015260208101905061203b565b50505050905001848103835286818151815260200191508051906020019060200280838360005b8381101561209857808201518184015260208101905061207d565b50505050905001848103825285818151815260200191508051906020019080838360005b838110156120d75780820151818401526020810190506120bc565b50505050905090810190601f1680156121045780820380516001836020036101000a031916815260200191505b5098505050505050505050602060405180830381600087803b15801561212957600080fd5b505af115801561213d573d6000803e3d6000fd5b505050506040513d602081101561215357600080fd5b81019080805190602001909291905050507bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614151561221e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f636f6e74726163742072657475726e656420616e20756e6b6e6f776e2076616c81526020017f75652066726f6d206f6e4241433030334261746368526563656976656400000081525060400191505060405180910390fd5b505050505050565b63fe20e3e67c0100000000000000000000000000000000000000000000000000000000027bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168473ffffffffffffffffffffffffffffffffffffffff1663fe20e3e688888787876040518663ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561236557808201518184015260208101905061234a565b50505050905090810190601f1680156123925780820380516001836020036101000a031916815260200191505b509650505050505050602060405180830381600087803b1580156123b557600080fd5b505af11580156123c9573d6000803e3d6000fd5b505050506040513d60208110156123df57600080fd5b81019080805190602001909291905050507bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415156124aa576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260388152602001807f636f6e74726163742072657475726e656420616e20756e6b6e6f776e2076616c81526020017f75652066726f6d206f6e4241433030335265636569766564000000000000000081525060400191505060405180910390fd5b5050505050505600a165627a7a7230582018542b0cd39c125ef1d8147246348809bca0d5c9381ce1c5d534ff743b9255640029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"_operator\",\"type\":\"address\"},{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_ids\",\"type\":\"uint256[]\"},{\"name\":\"_values\",\"type\":\"uint256[]\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"onBAC003BatchReceived\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes4\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_ids\",\"type\":\"uint256[]\"},{\"name\":\"_values\",\"type\":\"uint256[]\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"safeBatchTransferFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"totalAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owners\",\"type\":\"address[]\"},{\"name\":\"_ids\",\"type\":\"uint256[]\"}],\"name\":\"balanceOfBatch\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_uri\",\"type\":\"string\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"setURI\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"balance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_operator\",\"type\":\"address\"},{\"name\":\"_approved\",\"type\":\"bool\"}],\"name\":\"setApprovalForAll\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_to\",\"type\":\"address[]\"},{\"name\":\"_quantities\",\"type\":\"uint256[]\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"increase\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"nonce\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"destroy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_initialSupply\",\"type\":\"uint256\"},{\"name\":\"_uri\",\"type\":\"string\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"create\",\"outputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"creators\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"},{\"name\":\"_operator\",\"type\":\"address\"}],\"name\":\"isApprovedForAll\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"safeTransferFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_operator\",\"type\":\"address\"},{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"onBAC003Received\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes4\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_operator\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_value\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"TransferSingle\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_operator\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_ids\",\"type\":\"uint256[]\"},{\"indexed\":false,\"name\":\"_values\",\"type\":\"uint256[]\"}],\"name\":\"TransferBatch\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_owner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_operator\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_approved\",\"type\":\"bool\"}],\"name\":\"ApprovalForAll\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_value\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"URI\",\"type\":\"event\"}]";

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

    public static final String FUNC_CREATORS = "creators";

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

    public RemoteCall<byte[]> onBAC003BatchReceived(String _operator, String _from, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data) {
        final Function function = new Function(FUNC_ONBAC003BATCHRECEIVED,
                Arrays.<Type>asList(new Address(_operator),
                new Address(_from),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, Uint256.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, Uint256.class)),
                new DynamicBytes(_data)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> safeBatchTransferFrom(String _from, String _to, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, Uint256.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, Uint256.class)),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void safeBatchTransferFrom(String _from, String _to, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, Uint256.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, Uint256.class)),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String safeBatchTransferFromSeq(String _from, String _to, List<BigInteger> _ids, List<BigInteger> _values, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, Uint256.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_values, Uint256.class)),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> totalAmount(BigInteger _id) {
        final Function function = new Function(FUNC_TOTALAMOUNT,
                Arrays.<Type>asList(new Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> balanceOfBatch(List<String> _owners, List<BigInteger> _ids) {
        final Function function = new Function(FUNC_BALANCEOFBATCH,
                Arrays.<Type>asList(new DynamicArray<Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_owners, Address.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_ids, Uint256.class))),
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
                Arrays.<Type>asList(new Utf8String(_uri),
                new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setURI(String _uri, BigInteger _id, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETURI,
                Arrays.<Type>asList(new Utf8String(_uri),
                new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setURISeq(String _uri, BigInteger _id) {
        final Function function = new Function(
                FUNC_SETURI,
                Arrays.<Type>asList(new Utf8String(_uri),
                new Uint256(_id)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> balance(String _owner, BigInteger _id) {
        final Function function = new Function(FUNC_BALANCE,
                Arrays.<Type>asList(new Address(_owner),
                new Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setApprovalForAll(String _operator, Boolean _approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL,
                Arrays.<Type>asList(new Address(_operator),
                new Bool(_approved)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setApprovalForAll(String _operator, Boolean _approved, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL,
                Arrays.<Type>asList(new Address(_operator),
                new Bool(_approved)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setApprovalForAllSeq(String _operator, Boolean _approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL,
                Arrays.<Type>asList(new Address(_operator),
                new Bool(_approved)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> increase(BigInteger _id, List<String> _to, List<BigInteger> _quantities, byte[] _data) {
        final Function function = new Function(
                FUNC_INCREASE,
                Arrays.<Type>asList(new Uint256(_id),
                new DynamicArray<Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_to, Address.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_quantities, Uint256.class)),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void increase(BigInteger _id, List<String> _to, List<BigInteger> _quantities, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INCREASE,
                Arrays.<Type>asList(new Uint256(_id),
                new DynamicArray<Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_to, Address.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_quantities, Uint256.class)),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String increaseSeq(BigInteger _id, List<String> _to, List<BigInteger> _quantities, byte[] _data) {
        final Function function = new Function(
                FUNC_INCREASE,
                Arrays.<Type>asList(new Uint256(_id),
                new DynamicArray<Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_to, Address.class)),
                new DynamicArray<Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(_quantities, Uint256.class)),
                new DynamicBytes(_data)),
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
                Arrays.<Type>asList(new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void destroy(BigInteger _id, BigInteger _value, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DESTROY,
                Arrays.<Type>asList(new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String destroySeq(BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_DESTROY,
                Arrays.<Type>asList(new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> create(BigInteger _initialSupply, String _uri, byte[] _data) {
        final Function function = new Function(
                FUNC_CREATE,
                Arrays.<Type>asList(new Uint256(_initialSupply),
                new Utf8String(_uri),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(BigInteger _initialSupply, String _uri, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE,
                Arrays.<Type>asList(new Uint256(_initialSupply),
                new Utf8String(_uri),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createSeq(BigInteger _initialSupply, String _uri, byte[] _data) {
        final Function function = new Function(
                FUNC_CREATE,
                Arrays.<Type>asList(new Uint256(_initialSupply),
                new Utf8String(_uri),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> creators(BigInteger param0) {
        final Function function = new Function(FUNC_CREATORS,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isApprovedForAll(String _owner, String _operator) {
        final Function function = new Function(FUNC_ISAPPROVEDFORALL,
                Arrays.<Type>asList(new Address(_owner),
                new Address(_operator)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> safeTransferFrom(String _from, String _to, BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void safeTransferFrom(String _from, String _to, BigInteger _id, BigInteger _value, byte[] _data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String safeTransferFromSeq(String _from, String _to, BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<byte[]> onBAC003Received(String _operator, String _from, BigInteger _id, BigInteger _value, byte[] _data) {
        final Function function = new Function(FUNC_ONBAC003RECEIVED,
                Arrays.<Type>asList(new Address(_operator),
                new Address(_from),
                new Uint256(_id),
                new Uint256(_value),
                new DynamicBytes(_data)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public List<TransferSingleEventResponse> getTransferSingleEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERSINGLE_EVENT, transactionReceipt);
        ArrayList<TransferSingleEventResponse> responses = new ArrayList<TransferSingleEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERSINGLE_EVENT, log);
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
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERBATCH_EVENT, transactionReceipt);
        ArrayList<TransferBatchEventResponse> responses = new ArrayList<TransferBatchEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERBATCH_EVENT, log);
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
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
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
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(URI_EVENT, transactionReceipt);
        ArrayList<URIEventResponse> responses = new ArrayList<URIEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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
                EventValuesWithLog eventValues = extractEventParametersWithLog(URI_EVENT, log);
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

    public static RemoteCall<BAC003> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BAC003.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BAC003> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BAC003.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BAC003> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BAC003.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BAC003> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BAC003.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
