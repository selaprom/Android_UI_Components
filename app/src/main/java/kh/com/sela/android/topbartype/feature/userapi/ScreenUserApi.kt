package kh.com.sela.android.topbartype.feature.userapi

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.Util.LoadingUtil
import kh.com.sela.android.topbartype.domain.model.base.BaseUiState
import androidx.compose.ui.text.style.TextAlign
import kh.com.sela.android.topbartype.domain.model.request.UserModelRequest
import kh.com.sela.android.topbartype.domain.model.request.UserUpdateRequest
import kh.com.sela.android.topbartype.domain.model.response.UserModelResponse


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ScreenUserApi(
    userVM: UserVM = viewModel()
) {
    val userUiState by userVM.usersUiState.collectAsStateWithLifecycle()
    val userUpdateUiState by userVM.userUpdateUiState.collectAsStateWithLifecycle()
    var userId by remember { mutableIntStateOf(0) }
    var isEdit by remember { mutableStateOf(false) }
    val userCreateUiState by userVM.userUiState.collectAsStateWithLifecycle()
    var isShowModleButtomSheet by remember { mutableStateOf(false) }
    val userDeleteUiState by userVM.userDeleteUiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()

    var name by rememberSaveable() { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }

    val context = LocalContext.current
    var isShowDialog by remember { mutableStateOf(false) }
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateEmail(input: String) {
        emailError = when {
            input.isEmpty() -> ""
            !isValidEmail(input) -> "Invalid email format (e.g. user@example.com)"
            else -> ""
        }
    }

    val isFormValid = name.isNotBlank() && isValidEmail(email)

    fun onConfirm() {
        val body = UserModelRequest(
            fullname = name,
            email = email
        )
        userVM.createUser(body)
        name = ""
        email = ""
    }

    fun onEdite(){
        val body = UserUpdateRequest(
            id = userId,
            fullname = name,
            email = email
        )
        userVM.updateUser(body)
        name = ""
        email = ""
    }


    LaunchedEffect(key1 = userUiState) {
        when (val state = userUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }
    LaunchedEffect(key1 = userCreateUiState) {
        when (val state = userCreateUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                userVM.getUsers()
                LoadingUtil.hideLoading()


                val toast = Toast.makeText(context, "User Created", Toast.LENGTH_SHORT)
                toast.show()
                println(state.data)
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }
    LaunchedEffect(key1 = userDeleteUiState) {
        when (val state = userDeleteUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                userVM.getUsers()
                LoadingUtil.hideLoading()

                val toast = Toast.makeText(context, "${state.data.message}", Toast.LENGTH_SHORT)
                toast.show()
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }
    LaunchedEffect(key1 = userUpdateUiState) {
        when (val state = userUpdateUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                userVM.getUsers()
                LoadingUtil.hideLoading()


                val toast = Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT)
                toast.show()
                println(state.data)
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }
    LaunchedEffect(key1 = Unit) {
        userVM.getUsers()
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            userVM.onDispose()
        }
    }

    Scaffold(


        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 68.dp,
                title = {
                    Text("UsersApi", color = MaterialTheme.colorScheme.surface)
                },


                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton({}) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }
                },
                actions = {
                    IconButton({}) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }
                }

            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isShowModleButtomSheet = true
            }) {
                Icon(painter = painterResource(R.drawable.ic_add), contentDescription = null)
            }
        }

    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(top = 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when (val state = userUiState) {
                is BaseUiState.Success -> {
                    val users = (userUiState as BaseUiState.Success).data
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = rememberLazyListState(),

                        ) {
                        items(users, key = { user -> user.id }) { user ->
                            UserApiItem(
                                user = user,
                                onClickDelete = { id ->
                                    isShowDialog = true
                                    userId = user.id
                                },
                                onClickItem = {
                                    isShowModleButtomSheet = true
                                    userId = user.id
                                    name = user.fullname
                                    email = user.email
                                    isEdit = true
                                    validateEmail(email)
                                    isFormValid

                                }
                            )
                        }
                    }
                }

                else -> {}
            }
        }
        if (isShowDialog) {
            AlertDialog(
                shape = RoundedCornerShape(size = 10.dp),
                onDismissRequest = {
                    isShowDialog = false
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                title = {
                    Text(
                        "Message",
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 20.sp
                    )
                },
                text = {
                    Text(
                        text = "Do you want to delete User",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 18.sp
                    )
                },
                icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
                confirmButton = {
                    TextButton(onClick = {

                        userVM.deleteUser(userId)
                        isShowDialog = false

                    }) { Text("Ok") }
                },
                dismissButton = {
                    TextButton(onClick = {
                        isShowDialog = false
                    }) { Text("Cancel") }
                }

            )
        }
        if (isShowModleButtomSheet) {

            ModalBottomSheet(
                onDismissRequest = {
                    isShowModleButtomSheet = false
                    isEdit = false
                    name = ""
                    email = ""
                    emailError = ""
                    userId = 0

                },
                sheetState = sheetState
            ) {
                Column(


                    modifier = Modifier.fillMaxWidth(),

                    ) {

                    Text(

                        if (isEdit) "Update User" else "Create User",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = { input ->
                            // Restrict: letters and spaces only, max 50 chars
                            if (input.length <= 50 && input.all { it.isLetter() || it.isWhitespace() }) {
                                name = input
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        placeholder = { Text(text = "Enter your name") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words,

                            ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Email Field
                    OutlinedTextField(
                        value = email,
                        onValueChange = { input ->
                            // Restrict: no spaces allowed, max 100 chars
                            if (!input.contains(" ") && input.length <= 100) {
                                email = input
                                validateEmail(input)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        placeholder = { Text(text = "Enter your Email") },
                        isError = emailError.isNotEmpty(),
                        supportingText = {
                            if (emailError.isNotEmpty()) {
                                Text(
                                    text = emailError,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,


                            ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FilledTonalButton(
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                        enabled = isFormValid,

                        onClick = {
                            if (isEdit) {
                                onEdite()
                                isShowModleButtomSheet = false
                            } else {
                                onConfirm()
                                isShowModleButtomSheet = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(48.dp)
                    ) {
                        Text(if (isEdit) "Update" else "Create")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }
}

@Composable
fun UserApiItem(
    user: UserModelResponse,
    onClickDelete: (id: Int) -> Unit,
    onClickItem: () -> Unit
) {
    Card(
        onClick = { onClickItem() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(user.id.toString(), fontSize = 20.sp)

            Spacer(modifier = Modifier.width(8.dp))

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text("UserName: ${user.fullname}", fontSize = 20.sp)
                Text("Email: ${user.email}", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                onClickDelete(user.id)

            }) {
                Icon(
                    tint = Color.Red,
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen_ToolTip() {
    ScreenUserApi()
}

